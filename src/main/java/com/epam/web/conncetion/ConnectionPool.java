package com.epam.web.conncetion;

import com.epam.web.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = Logger.getRootLogger();

    private static final int TOTAL_CONNECTIONS = 5;

    private static ConnectionPool instance;

    private final Queue<ProxyConnection> availableConnections = new ArrayDeque<>();
    private final Set<ProxyConnection> connectionsInUse = new HashSet<>();

    private final ReentrantLock connectionsLock = new ReentrantLock();
    private final Semaphore semaphore = new Semaphore(TOTAL_CONNECTIONS);

    private ConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("No such class exception!" + e);
            throw new RuntimeException(e);
        }

        for (int i = 0; i < TOTAL_CONNECTIONS; i++) {
            try {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "123456"), this);
                availableConnections.offer(connection);
            } catch (SQLException e) {
                LOGGER.error("Cannot connect to database!" + e);
                throw new RuntimeException(e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = new ConnectionPool();
                    instance = localInstance;
                }
            }
        }
        return localInstance;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        connectionsLock.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                availableConnections.offer(proxyConnection);
                connectionsInUse.remove(proxyConnection);
                semaphore.release();
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() throws DaoException {
        connectionsLock.lock();
        try {
            semaphore.acquire();
            ProxyConnection connection = availableConnections.poll();
            connectionsInUse.add(connection);
            return connection;
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted while trying to get db connection", e);
            throw new DaoException(e);
        } finally {
            connectionsLock.unlock();
        }
    }
}

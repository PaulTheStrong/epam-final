package com.epam.web.exception;

public class DaoException extends Exception {
    public DaoException() {
    }

    public DaoException(String s) {
        super(s);
    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }
}

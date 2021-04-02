package com.epam.web.exceptions;

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

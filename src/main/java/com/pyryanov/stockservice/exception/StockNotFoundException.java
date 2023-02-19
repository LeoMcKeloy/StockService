package com.pyryanov.stockservice.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String msg) {
        super(msg);
    }
}

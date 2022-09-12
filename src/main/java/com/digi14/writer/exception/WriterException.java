package com.digi14.writer.exception;

public abstract class WriterException extends RuntimeException{
    public WriterException() {
    }

    public WriterException(String message) {
        super(message);
    }
}

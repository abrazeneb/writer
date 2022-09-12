package com.digi14.writer.exception;

import lombok.Data;

@Data
public class WriterNoFileFoundException extends WriterException {
    private String errorCode;

    public WriterNoFileFoundException() {
    }

    public WriterNoFileFoundException(String message) {
        super(message);
    }

    public WriterNoFileFoundException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}

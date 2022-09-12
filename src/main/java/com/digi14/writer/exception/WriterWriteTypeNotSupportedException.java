package com.digi14.writer.exception;

import lombok.Data;

@Data
public class WriterWriteTypeNotSupportedException extends WriterException {
    private final String errorCode = "WRITE_TYPE_NOT_SUPPORTED";
    public WriterWriteTypeNotSupportedException(final String errorMessage) {
        super(errorMessage);
    }
}

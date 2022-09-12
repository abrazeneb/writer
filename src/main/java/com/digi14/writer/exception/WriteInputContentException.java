package com.digi14.writer.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class WriteInputContentException extends WriterException{

    public WriteInputContentException() {
    }

    public WriteInputContentException(String message) {
        super(message);
    }
}

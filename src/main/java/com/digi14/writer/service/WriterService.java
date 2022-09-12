package com.digi14.writer.service;

import com.digi14.writer.service.dto.AbstractCustomWriterDto;
import com.digi14.writer.service.dto.AbstractResponseDto;

public interface WriterService {
    boolean supports(AbstractCustomWriterDto writerDto);
    boolean supports(String fileName);
    AbstractResponseDto write(AbstractCustomWriterDto writerDto);
    AbstractResponseDto readContent(final String target);
    AbstractResponseDto toUppercase(final String target);
    AbstractResponseDto toLowercase(final String target);
    AbstractResponseDto removeStupid(final String target);
    AbstractResponseDto removeDuplicate(final String target);
    void close(final String target);

}

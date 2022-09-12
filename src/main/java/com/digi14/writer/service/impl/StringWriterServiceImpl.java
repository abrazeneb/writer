package com.digi14.writer.service.impl;

import com.digi14.writer.service.dto.AbstractCustomWriterDto;
import com.digi14.writer.service.dto.AbstractResponseDto;
import com.digi14.writer.service.dto.StringWriteResponseDto;
import com.digi14.writer.service.dto.StringWriterRequestDto;
import com.digi14.writer.service.writers.CustomStringWriter;
import com.digi14.writer.service.writers.Writer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class StringWriterServiceImpl extends AbstractWriterService {
    private Writer writer = new CustomStringWriter("");

    @Override
    public boolean supports(final AbstractCustomWriterDto writerDto) {
        return writerDto instanceof StringWriterRequestDto;
    }

    @Override
    public boolean supports(String fileName) {
        return StringUtils.isBlank(fileName);
    }

    @Override
    public synchronized AbstractResponseDto write(final AbstractCustomWriterDto writerDto) {
        final StringWriterRequestDto stringWriterRequestDto = (StringWriterRequestDto)writerDto;
        writer.write(stringWriterRequestDto.getContent());
        return StringWriteResponseDto.builder().content(writer.getContent()).build();
    }

    @Override
    public AbstractResponseDto readContent(final String target) {
        return StringWriteResponseDto.builder().content(writer.getContent()).build();
    }

    @Override
    public AbstractResponseDto toUppercase(String target) {
        return StringWriteResponseDto.builder().content(writer.getContent()).build();
    }

    @Override
    public AbstractResponseDto toLowercase(String target) {
        return StringWriteResponseDto.builder().content(writer.getContent()).build();
    }

    @Override
    public AbstractResponseDto removeStupid(String target) {
        return StringWriteResponseDto.builder().content(writer.getContent()).build();
    }

    @Override
    public AbstractResponseDto removeDuplicate(String target) {
        return StringWriteResponseDto.builder().content(writer.getContent()).build();
    }

    @Override
    public void close(String target) {
        writer.close();
    }

}

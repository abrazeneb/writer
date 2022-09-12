package com.digi14.writer.service.impl;

import com.digi14.writer.exception.WriterNoFileFoundException;
import com.digi14.writer.service.dto.AbstractCustomWriterDto;
import com.digi14.writer.service.dto.AbstractResponseDto;
import com.digi14.writer.service.dto.FileWriterRequestDto;
import com.digi14.writer.service.dto.FileWriterResponseDto;
import com.digi14.writer.service.writers.CustomFileWriter;
import com.digi14.writer.service.writers.Writer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Service
public class FileWriterServiceImpl extends AbstractWriterService {

    private Map<String, CustomFileWriter> fileWriters = new HashMap<>();

    @Override
    public boolean supports(AbstractCustomWriterDto writerDto) {
        return writerDto instanceof FileWriterRequestDto;
    }

    @Override
    public boolean supports(String fileName) {
        return StringUtils.isNotBlank(fileName);
    }

    @Override
    public AbstractResponseDto write(final AbstractCustomWriterDto writerDto) {
        final FileWriterRequestDto fileWriterRequestDto = (FileWriterRequestDto) writerDto;

        CustomFileWriter customFileWriter = fileWriters.get(((FileWriterRequestDto) writerDto).getFileName());
        Writer fileWriter = nonNull(customFileWriter) ? customFileWriter : new CustomFileWriter(fileWriterRequestDto.getFileName());
        fileWriter = fileWriter.write(fileWriterRequestDto.getContent());

        fileWriters.put(((FileWriterRequestDto) writerDto).getFileName(), (CustomFileWriter)fileWriter);
        return FileWriterResponseDto.builder()
                                    .fileName(fileWriterRequestDto.getFileName())
                                    .content(fileWriter.readContent())
                                    .build();
    }

    @Override
    public AbstractResponseDto readContent(final String target) {
        CustomFileWriter customFileWriter = fileWriters.get(target);
        validate(customFileWriter);
        return FileWriterResponseDto.builder()
                                    .fileName(target)
                                    .content(customFileWriter.readContent())
                                    .build();
    }

    @Override
    public AbstractResponseDto toUppercase(String target) {
        CustomFileWriter customFileWriter = fileWriters.get(target);
        validate(customFileWriter);
        return FileWriterResponseDto.builder()
                                    .fileName(target)
                                    .content(customFileWriter.toUpperCase().readContent())
                                    .build();
    }

    @Override
    public AbstractResponseDto toLowercase(String target) {
        CustomFileWriter customFileWriter = fileWriters.get(target);
        validate(customFileWriter);
        return FileWriterResponseDto.builder()
                                    .fileName(target)
                                    .content(customFileWriter.toLowerCase().readContent())
                                    .build();
    }

    @Override
    public AbstractResponseDto removeStupid(String target) {
        CustomFileWriter customFileWriter = fileWriters.get(target);
        validate(customFileWriter);
        return FileWriterResponseDto.builder()
                                    .fileName(target)
                                    .content(customFileWriter.removeStupid().readContent())
                                    .build();
    }

    @Override
    public AbstractResponseDto removeDuplicate(String target) {
        CustomFileWriter customFileWriter = fileWriters.get(target);
        validate(customFileWriter);
        return FileWriterResponseDto.builder()
                                    .fileName(target)
                                    .content(customFileWriter.removeStupid().readContent())
                                    .build();
    }

    @Override
    public void close(String target) {
        CustomFileWriter customFileWriter = fileWriters.get(target);
        validate(customFileWriter);
        customFileWriter.close();
    }

    private void validate(CustomFileWriter customFileWriter) {
        if(customFileWriter == null) {
            throw new WriterNoFileFoundException("NO_FILE_TO_WRITE_TO_FOUND", "No Target exists");
        }
    }
}

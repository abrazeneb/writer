package com.digi14.writer.service.writers;

import com.digi14.writer.exception.WriteInputContentException;
import com.digi14.writer.exception.WriterNoFileFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;


@Slf4j
public class CustomFileWriter extends Writer {
    private String fileName;

    public CustomFileWriter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public synchronized Writer write(String content) {
        if(StringUtils.isBlank(content)) {
            throw  new WriteInputContentException("Content empty");
        }
        if(!this.isClosed) {
            try {

                Optional<File> fileToWriteToOptional = openFile(this.fileName);
                FileWriter writer = null;
                if(fileToWriteToOptional.isPresent()) {
                    final String existingContent = this.readContent();
                    writer = new FileWriter(fileToWriteToOptional.get());
                    writer.append(existingContent + " " + content);

                } else {
                    writer = new FileWriter(createFile(this.fileName));
                    writer.append(content);
                }
                writer.flush();
                writer.close();

            } catch (IOException e) {

                throw new WriterNoFileFoundException("NO_FILE_TO_WRITE_TO_FOUND", String.format("File with name %s not found", fileName));
            }
        }

        return this;
    }

    @Override
    public Writer toLowerCase() {
        this.content = readContent();
        super.toLowerCase();
        return writeAlteredInternal(this.content);
    }

    @Override
    public Writer toUpperCase() {
        this.content = readContent();
        super.toUpperCase();
        return writeAlteredInternal(this.content);
    }

    @Override
    public Writer removeStupid() {
        this.content = readContent();
        super.removeStupid();
        return writeAlteredInternal(this.content);
    }

    @Override
    public Writer removeDuplicateWords() {
        this.content = readContent();
        super.removeDuplicateWords();
        return writeAlteredInternal(this.content);
    }

    @Override
    public synchronized String readContent() {
        return FileWriterUtil.readFromFile(this.fileName);

    }

    private synchronized Optional<File> openFile(final String fileName) {
        log.info("Trying to read a file with the name [{}]", fileName);
        try {
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileName);
            return file.exists() ? Optional.of(file) : Optional.empty();

        } catch (IOException e) {
            log.error("File with name [{}] not found", fileName);
            return Optional.empty();
        }
    }

    public File createFile(final String fileName) {
        log.info("Trying to create a file with the name [{}]", fileName);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(".").getFile() + "/" + fileName);
        return file;
    }

    private Writer writeAlteredInternal(final String content) {
        if(!this.isClosed) {
            try {

                Optional<File> fileToWriteToOptional = openFile(this.fileName);
                FileWriter writer = null;
                if(fileToWriteToOptional.isPresent()) {
                    writer = new FileWriter(fileToWriteToOptional.get());
                    writer.append(content);

                }

                writer.flush();
                writer.close();

            } catch (IOException e) {

                throw new WriterNoFileFoundException("NO_FILE_TO_WRITE_TO_FOUND", String.format("File with name %s not found", fileName));
            }
        }

        return this;
    }
}

package com.digi14.writer.service.writers;

import com.digi14.writer.exception.WriterNoFileFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

@Slf4j
public class FileWriterUtil {

    public static String readFromFile(final String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            ClassLoader classLoader = FileWriterUtil.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
            reader.lines().filter(line -> !line.isEmpty()).forEach(line -> stringBuilder.append(line).append("\n"));
            reader.close();

        } catch (IOException ie) {
            log.error("IOException while reading file content");
            throw new WriterNoFileFoundException("NO_FILE_TO_READ_FROM_FOUND", String.format("File with name %s not found", fileName));
        }
        final String result = stringBuilder.toString();
        return StringUtils.isNotBlank(result)
               && result.endsWith("\n")
               ? result.substring(0, result.length() - 1)
               : result;
    }

    public static File createFile(final String fileName) {
        log.info("Trying to create a file with the name [{}]", fileName);
        ClassLoader classLoader = FileWriterUtil.class.getClassLoader();
        return new File(classLoader.getResource(".").getFile() + "/" + fileName);
    }

}

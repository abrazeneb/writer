package com.digi14.writer.service.writers;

import com.digi14.writer.exception.WriteInputContentException;
import com.digi14.writer.exception.WriterNoFileFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;


class CustomFileWriterTest {

    @Test
    void write_withEmptyContent_shouldThrowWriteInputContentException() {
        CustomFileWriter writer = new CustomFileWriter("file1");
        Assertions.assertThatExceptionOfType(WriteInputContentException.class).isThrownBy(() ->
            writer.write(""))
            .satisfies(exception -> Assertions.assertThat(exception.getMessage().equals("Content empty")));
    }

    @Test
    void readContent_fileMissing_shouldThrowWriterNoFileFoundException() {
        CustomFileWriter writer = new CustomFileWriter("non-existent-file");
        MockedStatic<FileWriterUtil> fileWriterUtilMockedStatic = Mockito.mockStatic(FileWriterUtil.class);
        fileWriterUtilMockedStatic.when(() ->FileWriterUtil.readFromFile(any())).thenThrow(new WriterNoFileFoundException("NO_FILE_TO_READ_FROM_FOUND", "File with name non-existent-file not found"));

        Assertions.assertThatExceptionOfType(WriterNoFileFoundException.class).isThrownBy(() ->
            writer.readContent())
                  .satisfies(exception -> Assertions.assertThat(exception.getErrorCode().equals("NO_FILE_TO_READ_FROM_FOUND")));

        fileWriterUtilMockedStatic.verify(() -> FileWriterUtil.readFromFile("non-existent-file"), Mockito.times(1));
    }

    @Test
    void write_withWriterClosed_shouldIgnoreWriteAttempt() {
        CustomFileWriter writer = new CustomFileWriter("file");
        writer.close();

        MockedStatic<FileWriterUtil> fileWriterUtilMockedStatic = Mockito.mockStatic(FileWriterUtil.class);
        writer.write("content");

        fileWriterUtilMockedStatic.verify(() -> FileWriterUtil.readFromFile("content"), Mockito.times(0));
        fileWriterUtilMockedStatic.verify(() -> FileWriterUtil.createFile("file"), Mockito.times(0));
        Mockito.verifyNoMoreInteractions(FileWriterUtil.class);
    }

    @Test
    public void toLowerCase_fileWithContent_shouldChangeContentToUppercase() {
        CustomFileWriter writer = new CustomFileWriter("file");
        final String content = "content";
        writer.write(content).toUpperCase();

        Assertions.assertThat(writer.readContent().equals("CONTENT"));
    }
}
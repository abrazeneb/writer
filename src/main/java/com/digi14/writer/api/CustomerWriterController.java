package com.digi14.writer.api;

import com.digi14.writer.exception.WriterWriteTypeNotSupportedException;
import com.digi14.writer.service.WriterService;
import com.digi14.writer.service.dto.AbstractCustomWriterDto;
import com.digi14.writer.service.dto.AbstractResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("writers")
@RequiredArgsConstructor
public class CustomerWriterController {

    private final List<WriterService> writerServices;

    @PostMapping
    @Operation(
        summary = "Write String Content.",
        description = "Write new string content to a string or file writer.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Content successfully written in."),
        @ApiResponse(responseCode = "400", description = "The received request is not syntactically valid. Correct the request and try again."),
        @ApiResponse(responseCode = "404", description = "The target doesn't exist.")
    })
    public ResponseEntity<AbstractResponseDto> writeContent(@Valid @RequestBody AbstractCustomWriterDto customWriterDto) {
        return ResponseEntity
            .ok()
            .body(writerServices
                      .stream()
                      .filter(writerService -> writerService.supports(customWriterDto))
                      .findFirst()
                      .orElseThrow(() -> {
                          log.error("No supporting service found");
                          return new WriterWriteTypeNotSupportedException("No supporting service found");
                      }).write(customWriterDto));
    }


    @GetMapping("/content")
    public ResponseEntity<AbstractResponseDto> readContent(@RequestParam(value = "fileName", required = false) final String fileName) {
        return ResponseEntity
            .ok()
            .body(writerServices
                      .stream()
                      .filter(writerService -> writerService.supports(fileName))
                      .findFirst()
                      .orElseThrow(() -> {
                          log.error("No supporting service found");
                          return new WriterWriteTypeNotSupportedException("No supporting service found");
                      }).readContent(fileName));
    }


    @PutMapping("/uppercase")
    public ResponseEntity<AbstractResponseDto> toUppercase(@RequestParam(value = "fileName", required = false) final String fileName) {
        return ResponseEntity
            .ok()
            .body(writerServices
                      .stream()
                      .filter(writerService -> writerService.supports(fileName))
                      .findFirst()
                      .orElseThrow(() -> {
                          log.error("No supporting service found");
                          return new WriterWriteTypeNotSupportedException("No supporting service found");
                      }).toUppercase(fileName));
    }


    @PutMapping("/lowercase")
    public ResponseEntity<AbstractResponseDto> toLowercase(@RequestParam(value = "fileName", required = false) final String fileName) {
        return ResponseEntity
            .ok()
            .body(writerServices
                      .stream()
                      .filter(writerService -> writerService.supports(fileName))
                      .findFirst()
                      .orElseThrow(() -> {
                          log.error("No supporting service found");
                          return new WriterWriteTypeNotSupportedException("No supporting service found");
                      }).toLowercase(fileName));
    }

    @PutMapping("/stupid-masked")
    public ResponseEntity<AbstractResponseDto> maskStupid(@RequestParam(value = "fileName", required = false) final String fileName) {
        return ResponseEntity
            .ok()
            .body(writerServices
                      .stream()
                      .filter(writerService -> writerService.supports(fileName))
                      .findFirst()
                      .orElseThrow(() -> {
                          log.error("No supporting service found");
                          return new WriterWriteTypeNotSupportedException("No supporting service found");
                      }).removeStupid(fileName));
    }

    @PutMapping("/no-duplicates")
    public ResponseEntity<AbstractResponseDto> removeDuplicates(@RequestParam(value = "fileName", required = false) final String fileName) {
        return ResponseEntity
            .ok()
            .body(writerServices
                      .stream()
                      .filter(writerService -> writerService.supports(fileName))
                      .findFirst()
                      .orElseThrow(() -> {
                          log.error("No supporting service found");
                          return new WriterWriteTypeNotSupportedException("No supporting service found");
                      }).removeDuplicate(fileName));
    }

    @PutMapping("/closed")
    public ResponseEntity<Void> close(@RequestParam(value = "fileName", required = false) final String fileName) {
        writerServices
            .stream()
            .filter(writerService -> writerService.supports(fileName))
            .findFirst()
            .orElseThrow(() -> {
                log.error("No supporting service found");
                return new WriterWriteTypeNotSupportedException("No supporting service found");
            }).close(fileName);

        return ResponseEntity.ok().build();
    }

}

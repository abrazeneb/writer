package com.digi14.writer.service.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonTypeName("file")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class FileWriterRequestDto extends AbstractCustomWriterDto {
    private static final long serialVersionUID = 2366104480589987193L;
    @NotBlank
    private String fileName;

}

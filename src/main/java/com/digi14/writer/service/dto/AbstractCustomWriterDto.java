package com.digi14.writer.service.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", defaultImpl = StringWriterRequestDto.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = StringWriterRequestDto.class, name = "string"),
    @JsonSubTypes.Type(value = FileWriterRequestDto.class, name = "file")

})
public abstract class AbstractCustomWriterDto implements Serializable {
    private static final long serialVersionUID = -2338258068081070079L;
    @NotBlank
    private String content;

}

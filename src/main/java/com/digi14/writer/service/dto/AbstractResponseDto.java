package com.digi14.writer.service.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = StringWriteResponseDto.class, name = "string"),
               @JsonSubTypes.Type(value = FileWriterResponseDto.class, name = "file")})
public abstract class AbstractResponseDto implements Serializable {
    private static final long serialVersionUID = 2392312924559079794L;
    private String content;

}

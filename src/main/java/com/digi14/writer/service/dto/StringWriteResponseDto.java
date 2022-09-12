package com.digi14.writer.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@JsonTypeName("string")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
public class StringWriteResponseDto extends AbstractResponseDto {
    private static final long serialVersionUID = -9033057269354896150L;
    @JsonIgnoreProperties
    private String topic;
}

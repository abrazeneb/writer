package com.digi14.writer.service.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonTypeName("string")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class StringWriterRequestDto extends AbstractCustomWriterDto {
    private static final long serialVersionUID = -3699859456406786545L;
}

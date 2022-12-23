package com.example.websecurity.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
public class CvDto {

    private Long id;

    @NotEmpty
    @NotNull
    private String description;
}

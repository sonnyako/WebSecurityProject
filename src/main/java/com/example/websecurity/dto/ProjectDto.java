package com.example.websecurity.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder(toBuilder = true)
public class ProjectDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    private double ratePerHour;

    @NotNull
    @NotEmpty
    private String rateCurrency;
}

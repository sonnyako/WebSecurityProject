package com.example.websecurity.dto;

import com.example.websecurity.dao.model.RoleName;
import com.example.websecurity.validation.ValidPassword;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder(toBuilder = true)
public class RegistrationDto {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;

    @NotNull
    private RoleName role;
}

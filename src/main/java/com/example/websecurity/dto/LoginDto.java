package com.example.websecurity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank(message = "Username cannot be blank or null")
    private String username;

//    @ValidPassword(groups = {
//            On.Create.class,
//            On.Update.class})
    @NotBlank(message = "Password cannot be blank or null")
    private String password;
}

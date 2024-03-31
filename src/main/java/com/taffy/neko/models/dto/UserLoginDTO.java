package com.taffy.neko.models.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginDTO {

    @NotNull
    private String userName;

    private String password;
}

package com.taffy.neko.models.dto;


import lombok.Data;

@Data
public class UpdateUserStatusDTO {
    private String id;

    private int isDeleted;
}

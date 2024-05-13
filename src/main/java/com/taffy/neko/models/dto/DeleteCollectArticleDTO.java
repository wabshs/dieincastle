package com.taffy.neko.models.dto;


import lombok.Data;

@Data
public class DeleteCollectArticleDTO {
    private String userId;

    private String articleId;
}

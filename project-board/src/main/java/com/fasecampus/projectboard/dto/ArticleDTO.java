package com.fasecampus.projectboard.dto;

import com.fasecampus.projectboard.domain.Article;

import java.io.Serializable;
import java.time.LocalDateTime;

// entityToDto, dtoToEntity 추가.
public record ArticleDTO(
        LocalDateTime createdAt,
        String createdBy,
        String title,
        String content,
        String hashtag
){
    public static ArticleDTO of(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        return new ArticleDTO(createdAt, createdBy, title, content, hashtag);
    }
}

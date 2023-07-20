package com.example.tdd;

import lombok.Data;

@Data
public class ArticleDto {
    private long id;
    private String title;
    private String content;

    public static ArticleDto fromEntity(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        return articleDto;
    }
}

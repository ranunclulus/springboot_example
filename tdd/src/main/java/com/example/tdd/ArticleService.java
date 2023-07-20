package com.example.tdd;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    public List<ArticleDto> findByTitle(String query) {
        List<ArticleDto> articleList = new ArrayList<>();
        for(Article article:articleRepository.findAllByTitleContains(query)) {
            articleList.add(ArticleDto.fromEntity(article));
        }
        return articleList;
    }
}

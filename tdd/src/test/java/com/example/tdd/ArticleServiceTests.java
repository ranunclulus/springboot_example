package com.example.tdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTests {
    private ArticleService articleService;
    private ArticleRepository articleRepository;

    @Test
    public void searchByTitle() {
        // given
        String query = "Title";
        Article found = new Article();
        found.setTitle("Title here");
        found.setContent("Test Content");

        // mock
        when(articleRepository.findAllByTitleContains(query))
                .thenReturn(Collections.singletonList(found));

        // when
        // ArticleService가 어떻게 동작했으면 하는지
        List<ArticleDto> articleDtoList = articleService.findByTitle(query);

        // then
        assertEquals(1, articleDtoList.size());
        assertTrue(articleDtoList.get(0).getTitle().contains(query));
    }
}

package com.example.tdd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class AlticleRepositoryTests {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void articleRepositoryIsNotNull() {
        assertThat(articleRepository).isNotNull();
    }

    @Test
    public void createArticle() {
        //given
        //기본 배경
        Article article = new Article();
        article.setTitle("Hello");
        article.setContent("new content");

        //when
        //테스트하고 싶은 기능
        Article result = articleRepository.save(article);

        //then
        //테스트의 동작이 기대한 대로 이루어지는지
        assertThat(result.getId()).isNotNull();
        assertEquals(result, article);
    }

    @Test
    public void findByTitleContains() {
        // given
        Article article = new Article();
        article.setTitle("Hello");
        articleRepository.save(article);
        article = new Article();
        article.setTitle("Yellow");
        articleRepository.save(article);

        // when
        List<Article> result1 = articleRepository.findAllByTitleContains("ell");
        List<Article> result2 = articleRepository.findAllByTitleContains("He");
        List<Article> result3 = articleRepository.findAllByTitleContains("A");


        // then
        assertEquals(2, result1.size());
        assertEquals(1, result2.size());
        assertEquals(0, result3.size());
    }
}

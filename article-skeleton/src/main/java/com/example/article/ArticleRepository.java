package com.example.article;

import com.example.article.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository
        extends JpaRepository<ArticleEntity, Long> {
    List<ArticleEntity> findTop20ByOrderByIdDesc();
    List<ArticleEntity> findTop20ByIdLessThanOrderByIdDesc(Long id);
}

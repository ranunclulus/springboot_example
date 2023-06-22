package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    public ArticleDto createArticle(ArticleDto dto) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(dto.getTitle());
        articleEntity.setContent(dto.getContent());
        articleEntity.setWriter(dto.getWriter());
        return ArticleDto.fromEntity(repository.save(articleEntity));
    }

    public ArticleDto readArticle(Long id) {
        Optional<ArticleEntity> target = repository.findById(id);
        if(target.isPresent()) {
            ArticleDto articleDto = ArticleDto.fromEntity(target.get());
            return articleDto;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<ArticleDto> readArticleAll() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (ArticleEntity entity : repository.findAll()) {
            ArticleDto articleDto = new ArticleDto();
            articleDto.setTitle(entity.getTitle());
            articleDto.setContent(entity.getContent());
            articleDto.setWriter(entity.getWriter());
            articleDtos.add(articleDto);
        }
        return articleDtos;
    }

    public ArticleDto updateArticle(Long id, ArticleDto dto) {
        Optional<ArticleEntity> optionalArticle = repository.findById(id);
        if (optionalArticle.isPresent()) {
            ArticleEntity articleEntity = optionalArticle.get();
            articleEntity.setTitle(dto.getTitle());
            articleEntity.setContent(dto.getContent());
            articleEntity.setWriter(dto.getWriter());
            repository.save(articleEntity);
            return ArticleDto.fromEntity(articleEntity);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteArticle(Long id) {
        Optional<ArticleEntity> target = repository.findById(id);
        if(target.isPresent()) {
            repository.delete(target.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

  /*  public List<ArticleDto> readArticlePages() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (ArticleEntity entity:
             repository.findTop20ByOrderByIdDesc()) {
            articleDtos.add(ArticleDto.fromEntity(entity));
        }
        return articleDtos;
    }*/

    public Page<ArticleEntity> readArticlePages() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<ArticleEntity> articleEntityPage =
                repository.findAll(pageable);


       return articleEntityPage;
    }
}

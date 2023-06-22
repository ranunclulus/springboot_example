package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
// 어노테이션 붙이기
@RequiredArgsConstructor
@RestController
@RequestMapping("articles")
public class ArticleController {
    private final ArticleService service;

    // POST /articles
    @PostMapping
    // RESTful한 API는 행동의 결과로 반영된 자원의 상태를 반환하는 것이 옳다.
    public ArticleDto create(@RequestBody ArticleDto dto) {
        // RequestBody를 붙이면 역직렬화를 해 준다 (JSON -> 객체)
        return service.createArticle(dto);
    }


    // GET /articles?page=3&size=20
    @GetMapping
    public Page<ArticleDto> readAll(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size
    ) {
        return service.readArticlePages(page, size);
    }

    // GET /articles/{id}
    @GetMapping("/{id}")
    public ArticleDto read(@PathVariable("id") Long id) {
        return service.readArticle(id);
    }


    // PUT /articles/{id}
    @PutMapping("/{id}")
    public ArticleDto update(
            @PathVariable("id") Long id,
            @RequestBody ArticleDto dto
            ) {
        return service.updateArticle(id, dto);
    }


    // DELETE /articles/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteArticle(id);
    }

    // GET /articles/page-test
    @GetMapping("/page-test")
    public Page<ArticleDto> readPageTest() {
        return service.readArticlePages();
    }

}

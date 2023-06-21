package com.example.article;

import com.example.article.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
// 어노테이션 붙이기
@RequiredArgsConstructor
@RestController
public class ArticleController {
    private final ArticleService service;

    // POST /articles
    @PostMapping("/articles")
    // RESTful한 API는 행동의 결과로 반영된 자원의 상태를 반환하는 것이 옳다.
    public ArticleDto create(@RequestBody ArticleDto dto) {
        // RequestBody를 붙이면 역직렬화를 해 준다 (JSON -> 객체)
        return service.createArticle(dto);
    }


    // GET /articles
    @GetMapping("/articles")
    public List<ArticleDto> readAll() {
        return service.readArticleAll();
    }

    // GET /articles/{id}


    // PUT /articles/{id}


    // DELETE /articles/{id}

}

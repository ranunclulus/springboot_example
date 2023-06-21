package com.example.article;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
// 어노테이션 붙이기
@RequiredArgsConstructor
@RestController
public class ArticleController {
    private final ArticleService service;

    // POST /articles
    public void create() {

    }


    // GET /articles


    // GET /articles/{id}


    // PUT /articles/{id}


    // DELETE /articles/{id}

}

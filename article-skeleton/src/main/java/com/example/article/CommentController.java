package com.example.article;

import com.example.article.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/articles/{articleId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentDto create(
            @PathVariable("articleId") Long articleId,
            @RequestBody CommentDto dto) {
        return commentService.createComment(articleId, dto);
    }

    // TODO 게시글 댓글 전체 조회
    // GET /articles/{articleID}/comments
    @GetMapping
    public List<CommentDto> readAll(
            @PathVariable("articleId") Long articleId
    ){
        return commentService.readCommentAll(articleId);
    }

    // TODO 게시글 댓글 수정
    // PUT /articles/{articleID}/comments/{commentId}

    // TODO 게시글 댓글 삭제
    // DELETE /articles/{articleID}/comments/{commentID}
}

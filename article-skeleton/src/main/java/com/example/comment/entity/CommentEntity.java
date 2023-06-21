package com.example.comment.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String writer;
    private String content;
    private Long articleId;

}

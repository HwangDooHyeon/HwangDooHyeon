package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 45)
    private String writer;

    // 내용
    @Column (length = 300)
    private String contents;

    @Column
    private LocalDateTime createdTime;

    // 연관관계 매핑 다:1 (댓글:게시글)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(Long id, String writer, String contents, LocalDateTime createdTime, Board board) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
        this.createdTime = createdTime;
        this.board = board;
    }
}

package com.example.demo.DTO;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import lombok.*;

import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;

    private String writer;

    private String contents;

    private LocalDateTime createdTime;

    private Long boardId;


    public Comment toEntity(Board board) {
        return Comment.builder()
                .id(id)
                .writer(writer)
                .contents(contents)
                .createdTime(createdTime)
                .board(board)
                .build();

    }
}

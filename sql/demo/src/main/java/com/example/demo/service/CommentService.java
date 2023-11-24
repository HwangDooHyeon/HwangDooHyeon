package com.example.demo.service;

import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.DTO.CommentDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment save(CommentDTO commentDTO) {
        Optional<Board> optionalComment = boardRepository.findById(commentDTO.getBoardId());
        commentDTO.setCreatedTime(LocalDateTime.now());

        if(optionalComment.isPresent()) {
            Board board = optionalComment.get();
            return commentRepository.save(commentDTO.toEntity(board));
        } else {
            return null;
        }


    }
}

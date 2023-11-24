package com.example.demo.controller;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO) {

        Comment comment= commentService.save(commentDTO);

        if(comment != null) {
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("게시글이 없음.", HttpStatus.OK);
        }

    }


}

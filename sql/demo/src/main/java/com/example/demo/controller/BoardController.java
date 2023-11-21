package com.example.demo.controller;

import com.example.demo.service.BoardService;
import com.example.demo.DTO.BoardDTO;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String home() {
        return "createBoard";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println(boardDTO.getBoardTitle() + ": " + boardDTO.getBoardContents());
        boardService.save(boardDTO);
        return "";
    }

}

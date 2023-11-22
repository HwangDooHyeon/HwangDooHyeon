package com.example.demo.controller;

import com.example.demo.service.BoardService;
import com.example.demo.DTO.BoardDTO;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
//@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/createBoard")
    public String createBoard() {
        return "createBoard";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "redirect:";
    }


    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boards = boardService.paging(pageable);

        // 보여지는 페이지 수
        int blockLimit = 3;

        // 보여지는 페이지 시작 번호
        int startPage = (int)(Math.ceil((double)pageable.getPageNumber() / blockLimit) - 1) * blockLimit + 1;

        // 보여지는 페이지 끝 번호
        int endPage = ((startPage + blockLimit - 1) < boards.getTotalPages()) ? (startPage + blockLimit - 1) : boards.getTotalPages();

        //
        model.addAttribute("boardList", boards);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        return "paging";
    }


}

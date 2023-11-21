package com.example.demo.service;

import com.example.demo.repository.BoardRepository;
import com.example.demo.DTO.BoardDTO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        boardRepository.save(boardDTO.toEntity());
    }

}

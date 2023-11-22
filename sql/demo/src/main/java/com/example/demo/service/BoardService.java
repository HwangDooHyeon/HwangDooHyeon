package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.DTO.BoardDTO;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        boardDTO.setCreate_time(LocalDateTime.now());
        boardRepository.save(boardDTO.toEntity());
    }


    // 페이징(paging)을 위한 메서드
    // Pageable: 페이지의 수량 정보를 가지고 있는 인터페이스
    public Page<BoardDTO> paging(Pageable pageable) {

        // 페이지를 1번부터 시작하게 하려는 코드. 0부터 시작되게 하려면 -1 지우기.
        int page = pageable.getPageNumber() -1;

        // 페이지 당 개시물 수
        int size = 5;

        // 전체 게시물을 불러 온다.
        Page<Board> boards = boardRepository.findAll(
                // 정렬 처리해서 가져옴
                PageRequest.of(page, size)
        );

        // 람다식. board 객체 마다 boardDTO를 반환
        return boards.map(board -> new BoardDTO(
                board.getId(),
                board.getBoardTitle(),
                board.getBoardContents(),
                board.getCreate_time()));

    }


}

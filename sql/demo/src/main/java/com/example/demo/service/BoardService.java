package com.example.demo.service;

import com.example.demo.DTO.FileDTO;
import com.example.demo.entity.BoardFile;
import com.example.demo.repository.BoardRepository;
import com.example.demo.DTO.BoardDTO;
import com.example.demo.entity.Board;

import com.example.demo.repository.FileRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    // 학원에서는 /G/
    String filePath = "C:/Users/G/Desktop/HwangDooHyeon/sql/Board Files/";

    // 집에서는 /본인 Pc 이름/
//    String filePath = "C:/Users/geg55/OneDrive/Desktop/HwangDooHyeon/sql/Board Files/";


    @Transactional
    public void save(BoardDTO boardDTO, FileDTO fileDTO, MultipartFile[] files) throws IOException {
        boardDTO.setCreate_time(LocalDateTime.now());

        // 업로드 경로
        Path uploadPath = Paths.get(filePath);

        // 만약 경로가 없다면 -> 생성
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // UUID
        String uuid = UUID.randomUUID().toString();

        // 게시판을 db에 저장하고, 저장된 게시판의 아이디를 Long id로 지정
        Long id = boardRepository.save(boardDTO.toEntity()).getId();
        Board board = boardRepository.findById(id).get();

        // ** 파일 정보 저장
        for (MultipartFile file : files) {

            // 업로드 된 파일명 추출
            String originalFileName = file.getOriginalFilename();

            // 업로드 된 파일의 확장자 추출
            String formatType = originalFileName.substring(
                    originalFileName.lastIndexOf("."));

            // Path
            String path = filePath + uuid + originalFileName;

            // 경로에 파일을 저장 (db 아님)
            file.transferTo(new File(path));

            BoardFile boardFile = BoardFile.builder()
                    .filePath(path)
                    .fileName(originalFileName)
                    .uuid(uuid)
                    .fileType(formatType)
                    .fileSize(file.getSize())
                    .board(board)
                    .build();

            fileRepository.save(boardFile);
        }
    }


    private String createFilePath(MultipartFile file) throws IOException {

        // 업로드 경로
        Path uploadPath = Paths.get(filePath);

        // 만약 경로가 없다면 -> 생성
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 업로드 된 파일명 추출
        String originalFileName = file.getOriginalFilename();

        // UUID
        String uuid = UUID.randomUUID().toString();

        // 저장할 파일의 경로 설정
        Path path = uploadPath.resolve(
                uuid + originalFileName
        );

        Files.copy(
                file.getInputStream(),
                path,
                StandardCopyOption.REPLACE_EXISTING
        );

        return String.valueOf(path);
    }


    private String createFileName(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();

        if (originalFileName != null && !originalFileName.isEmpty() && originalFileName.contains(".")) {
            originalFileName = originalFileName.substring(
                    0, originalFileName.lastIndexOf("."));
        }  return originalFileName;
    }


    private String createFileType(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String formatType = "";

        if (originalFileName != null && !originalFileName.isEmpty() && originalFileName.contains(".")) {
            formatType = originalFileName.substring(
                    originalFileName.lastIndexOf("."));
        } return formatType;

    }


    private Long createFileSize(MultipartFile file) {
        Long fileSize = file.getSize();

        return fileSize;
    }


    public BoardDTO findById(Long Id) {

//        if(boardRepository.findById(Id).isPresent()) ... 예외처리 생략

        Board board = boardRepository.findById(Id).get();
        return BoardDTO.toBoardDTO(board);
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
                board.getCreate_time(),
                board.getUpdate_time()
        ));
    }


    @Transactional
    public void updatePost(BoardDTO boardDTO) {
        Optional<Board> boardOptional = boardRepository.findById(boardDTO.getId());

        Board board = boardOptional.get();

        board.updateFromDTO(boardDTO);

        boardRepository.save(board);
    }


    @Transactional
    public void delete(Long Id) {
        boardRepository.deleteById(Id);
    }

}

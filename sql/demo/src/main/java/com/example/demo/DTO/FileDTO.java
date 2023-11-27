package com.example.demo.DTO;

import com.example.demo.entity.Board;
import com.example.demo.entity.File;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDTO {

    // 파일 경로
    private String filePath;

    // 파일 이름
    private String fileName;

    // 파일 포멧
    private String fileType;

    // 파일 크기
    private Long fileSize;

    // 게시물 ID
    private Long boardId;

    public File toEntity() {
        return File.builder()
                .filePath(filePath)
                .fileName(fileName)
                .fileType(fileType)
                .fileSize(fileSize)
                .build();
    }

}

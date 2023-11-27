package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class File {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long fildId;

    // 파일 경로
    @Column
    private String filePath;

    // 파일 이름
    @Column
    private String fileName;

    // 파일 포멧
    @Column
    private String fileType;

    // 파일 크기
    @Column
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public File(Long fildId, String filePath, String fileName, String fileType, Long fileSize, Board board) {
        this.fildId = fildId;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.board = board;
    }
}

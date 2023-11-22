package com.example.demo.entity;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
public class Board {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    // 작성자 이메일
    @Column (length = 100)
    private String email;

    // 작성자 이름
    @Column (length = 45)
    private String user_name;

    // 제목
    @Column (length = 50)
    private String boardTitle;

    // 내용
    @Column (length = 300)
    private String boardContents;

    // 최초 작성 시간
    @Column
    private LocalDateTime create_time;

    // 최근 수정 시간
    @Column
    private LocalDateTime update_time;

    @Builder
    public Board(Long id, String email, String user_name, String boardTitle, String boardContents, LocalDateTime create_time, LocalDateTime update_time) {
        this.Id = id;
        this.email = email;
        this.user_name = user_name;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.create_time = create_time;
        this.update_time = update_time;
    }

}

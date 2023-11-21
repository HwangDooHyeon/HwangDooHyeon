package com.example.demo.DTO;

import com.example.demo.entity.Board;

import lombok.*;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

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

    public Board toEntity() {
        return Board.builder()
                .email(email)
                .user_name(user_name)
                .boardTitle(boardTitle)
                .boardContents(boardContents)
                .create_time(create_time)
                .update_time(update_time)
                .build();
    }

}

package com.example.demo.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 기본 생성자를 대신 만들어주는 어노테이션 (접근 레벨을 프로텍티드로 지정)

@Entity // 테이블을 만들겠다는 뜻
// **제약사항**
// 1. -1 @Entity 어노테이션이 붙어있는 클래스는 기본생성자를 가지고 있어야 한다.
// 1. -2 'public' 또는 'protected' 접근 수준을 가져야 한다.
// 2. @Entity 어노테이션이 붙어있는 클래스는 상속을 받거나, 다른 Entity를 상속받을 수 있다.
// 3. -1 Entity 클래스의 필드는 관계형 맵핑을 위해서 다른 어노테이션을 추가할 수 있다.
// 3. -2 Ex) @Column / @Id / @OneToMany / @ManyToOne 등등...

// 테이블을 만들어 주는 어노테이션 (테이블 이름을 "user"로 설정)
@Table(name = "com/example/demo/user")
public class User {

    @Id // 해당 필드를 PK로 설정하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 자동 설정
    private int id;

    @Column(length = 45, nullable = false) // 해당 필드값을 DB에서 열로 설정 (길이를 45로 설정, null 불가능)
    private String userName;

    @Column(length = 100, nullable = false, unique = true) // (길이를 100으로 설정, null 불가능, 이 값을 유일한 값으로 설정(값이 중복될 수 없음))
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    // 빌더 패턴을 쉽게 구현할 수 있도록 해준다.
    // 주로 인자가 많거나, 인자를 선택적으로 지정해야 하는 경우에 사용 (거의 매번 사용됨)
    @Builder
    public User(int id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

}

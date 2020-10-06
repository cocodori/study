package com.webex.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity //테이블과 링크할 클래스
public class Posts {

    @Id //PK
    /*
    *  @GeneratedValue
    *   PK생성 규칙을 나타낸다.
    *   GenerationType.IDENTITY 옵션을 추가해야
    *   auto_increament 된다.
    * */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    *  @Column은 선언하지 않아도 모두 컬럼이 된다.
    *  다른 속성을 줘야 할 때 @Column을 사용할 수 있다.
    * */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}


package com.webex.web.dto;

import com.webex.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
*   Entity는 데이터베이스와 맞닿은 핵심 클래스다.
*   따라서 Entity클래스를 직접 Request, Response하는 데 이용하면 위험이 크다.
*   변경 되어도 다른 클래스에 영향을 미치지 않는 DTO클래스를 따로 만들어서 데이터를 운반한다.
* */

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

package com.webex.web.dto;

import com.webex.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;


/*
*   게시글 전체 목록을 조회한 데이터를
*   화면으로 보내기 위한 DTO
* */
@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime createDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.createDate = entity.getCreatedDate();
    }
}

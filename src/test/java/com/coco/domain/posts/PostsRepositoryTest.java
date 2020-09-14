package com.coco.domain.posts;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        System.out.println("===================AfterEach===================");
        postsRepository.deleteAll();
    }

    @Test
    public void testBaseTimeEntity() {
        LocalDateTime now = LocalDateTime.of(2020,9,15,0,0,0);

        //insert
        postsRepository.save(Posts.builder()
            .title("title")
            .content("content")
            .author("author")
            .build());

        //조회
        List<Posts> postsList = postsRepository.findAll();

        //첫 번째 게시물
        Posts post = postsList.get(0);

        System.out.print(">>>>>>>>>>>>>>>>>>>>> createDate : " + post.getCreatedDate() +
                " modifiedDate : " + post.getModifiedDate());

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }

    @Test
    public void getPosts() {
        //저장
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author("coco@coco.com")
                        .build());

        //게시글 불러오기
        List<Posts> postsList = postsRepository.findAll();

        //테스트
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}

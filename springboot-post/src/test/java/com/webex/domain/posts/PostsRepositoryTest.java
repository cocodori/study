package com.webex.domain.posts;

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
    PostsRepository postsRepository;

    @AfterEach  //JUnit4의 @After
    public void cleanup() {
        //테스트 종료할 때마다 모든 데이터를 지운다.
        postsRepository.deleteAll();
    }

    /*
    *   given-when-then 패턴에 대해서는
    *   참고 - https://brunch.co.kr/@springboot/292
    * */
    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                                .title(title)
                                .content(content)
                                .author("tester")
                                .build());

        //when
        List<Posts> postList = postsRepository.findAll();

        //then
        Posts posts = postList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void baseEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                                .title("title")
                                .content("ontent")
                                .author("author")
                                .build());
        //when
        List<Posts> postList = postsRepository.findAll();

        //then
        Posts posts = postList.get(0);

        System.out.println(" >>>>>>>>>>>>>> createDate   : " + posts.getCreatedDate());
        System.out.println(" >>>>>>>>>>>>>> modifiedDate : " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate())
                .isAfter(now);
        assertThat(posts.getModifiedDate())
                .isAfter(now);

    }

}

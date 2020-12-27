package com.webex.web;

import com.webex.domain.posts.Posts;
import com.webex.domain.posts.PostsRepository;
import com.webex.web.dto.PostsSaveRequestDto;
import com.webex.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_등록한다() throws Exception {
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port +"/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle())
                .isEqualTo(title);
        assertThat(all.get(0).getContent())
                .isEqualTo(content);
    }

    @Test
    public void posts_수정한다() {
        //given
        Posts savedPost = postsRepository.save(Posts.builder()
                                        .title("title")
                                        .content("content")
                                        .author("author")
                                        .build());

        Long updateId = savedPost.getId();
        String expectedTitle = "수정한 제목";
        String expectedContent = "수정한 내용";

        PostsUpdateRequestDto requestDto
                = PostsUpdateRequestDto.builder()
                    .title(expectedTitle)
                    .content(expectedContent)
                    .build();

        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        //responseEntity.getBody()가 0L보다 크다면 테스트 통과
        assertThat(responseEntity.getBody())
                .isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle())
                .isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent())
                .isEqualTo(expectedContent);

        System.out.println("--------------------------------------------");
        System.out.println("responseEntity.getStatusCode() : " + responseEntity.getStatusCode());
        System.out.println("responseEntity.getBody() : " + responseEntity.getBody());
        System.out.println("--------------------------------------------");
    }

    @Test
    public void post_삭제한다() {
        //given
        Posts post = postsRepository.save(Posts.builder()
                                    .title("title")
                                    .content("content")
                                    .author("cococ")
                                    .build());

        assertThat(postsRepository.findById(1L))
                .isNotEmpty();

        String url = "http://localhost:"+port+"/api/v1/posts/"+post.getId();

        //when
        restTemplate.delete(url);

        //then
        assertThat(postsRepository.findById(1L))
                .isEmpty();
    }
}

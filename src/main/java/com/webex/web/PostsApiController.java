package com.webex.web;

import com.webex.service.posts.PostsService;
import com.webex.web.dto.PostsResponseDto;
import com.webex.web.dto.PostsSaveRequestDto;
import com.webex.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    //등록 api
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    //수정 api
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    //조회 api
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    //삭제 api
    @DeleteMapping("/api/v1/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postsService.delete(id);

        return id;
    }
}

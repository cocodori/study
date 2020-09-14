package com.coco.service.posts;

import com.coco.domain.posts.Posts;
import com.coco.domain.posts.PostsRepository;
import com.coco.web.dto.PostsResponseDto;
import com.coco.web.dto.PostsSaveRequestDto;
import com.coco.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id : "+id));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    //하나의 게시물 엔티티를 불러와서 dto로 변환하는 메서드
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id :" + id));

        return new PostsResponseDto(entity);
    }
}

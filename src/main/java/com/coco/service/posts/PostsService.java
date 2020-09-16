package com.coco.service.posts;

import com.coco.domain.posts.Posts;
import com.coco.domain.posts.PostsRepository;
import com.coco.web.dto.PostsListResponseDto;
import com.coco.web.dto.PostsResponseDto;
import com.coco.web.dto.PostsSaveRequestDto;
import com.coco.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    /*
    * postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해
    * PostsListResponseDto로 변환해서 List로 반환하는 메서드
    * */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> fildAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // == posts -> new PostsListResponseDto(posts)와 동일
                .collect(Collectors.toList());
    }

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

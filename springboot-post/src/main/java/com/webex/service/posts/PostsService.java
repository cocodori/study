package com.webex.service.posts;

import com.webex.domain.posts.Posts;
import com.webex.domain.posts.PostsRepository;
import com.webex.web.dto.PostsListResponseDto;
import com.webex.web.dto.PostsResponseDto;
import com.webex.web.dto.PostsSaveRequestDto;
import com.webex.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id : " + id));

        return new PostsResponseDto(entity);
    }

    /*
    *   @Transaction의 readonly=true 속성은
    *   트랜잭션 범위는 유지하되,
    *   조회 기능만 남겨두어 조회 속도가 개선된다.
    *   등록, 수정, 삭제 기능이 없는 서비스 메소드에서 사용하면 좋다.
    * */

    /*
    *   postsRepository결과로 넘어온 Posts의 Stream을 map을 통해
    *   PostsListResponseDto로 변환해서 List에 담아 반환하는 메소드
    * */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // == .map(posts -> new PostsListResponseDto(posts))와 동일한 코드
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "번 게시물이 없습니다."));

        postsRepository.delete(post);
    }
}

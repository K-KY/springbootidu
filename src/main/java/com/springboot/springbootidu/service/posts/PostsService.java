package com.springboot.springbootidu.service.posts;

import com.springboot.springbootidu.domain.posts.Posts;
import com.springboot.springbootidu.domain.posts.PostsRepository;
import com.springboot.springbootidu.web.dto.PostsListResponseDto;
import com.springboot.springbootidu.web.dto.PostsResponseDto;
import com.springboot.springbootidu.web.dto.PostsSaveRequestDto;
import com.springboot.springbootidu.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        System.out.println("requestDto InPostsService  = " + requestDto);
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    //@Transactional
    // 메소드를 실행하면서 오류가 있다면 실행 전으로 되돌리는 기능
    // 오류가 없으면 데이터 베이스에 반영

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;

    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAll().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }
}
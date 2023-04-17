package com.springboot.springbootidu.service.posts;

import com.springboot.springbootidu.domain.posts.Posts;
import com.springboot.springbootidu.domain.posts.PostsRepository;
import com.springboot.springbootidu.web.dto.PostsResponseDto;
import com.springboot.springbootidu.web.dto.PostsSaveRequestDto;
import com.springboot.springbootidu.web.dto.PostsUpdateRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;

    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new PostsResponseDto(entity);
    }
}
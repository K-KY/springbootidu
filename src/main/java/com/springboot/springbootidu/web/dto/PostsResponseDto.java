package com.springboot.springbootidu.web.dto;

import com.springboot.springbootidu.domain.posts.Posts;
import lombok.*;

@Getter

public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        //생성자 인자로 Posts 를 넣은 이유
        //여기서 사용하는 필드가 Posts 의 필드를 일부 사용하기 때문
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
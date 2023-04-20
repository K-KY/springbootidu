package com.springboot.springbootidu.web.dto;

import com.springboot.springbootidu.domain.posts.Posts;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        System.out.println("PostsSaveRequestDto Builder");
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        System.out.println("postsSaveRequestDto: ToENTITY");
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
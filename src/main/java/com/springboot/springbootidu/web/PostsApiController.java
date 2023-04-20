package com.springboot.springbootidu.web;

import com.springboot.springbootidu.service.posts.PostsService;
import com.springboot.springbootidu.web.dto.PostsResponseDto;
import com.springboot.springbootidu.web.dto.PostsSaveRequestDto;
import com.springboot.springbootidu.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostsApiController {
    private final PostsService postsService;

    public PostsApiController(PostsService postsService) {
        this.postsService = postsService;
    }
    //원래라면 PostService 에 @Autowited를 붙여서 bean 을 주입받았지만 지금은 final 로 선언해 컨스트럭터로 @Autowired 와 동일한 효과를 낸다


    @PostMapping("/api/v1/posts")
    public Long save(
            @RequestBody
            PostsSaveRequestDto requestDto) {
        System.out.println("requestDto = " + requestDto);
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }
    //@PathVariable
    //requestUrl 에 포함된 {id} 를 변수로 사용한다
    // 변수 값과 {} 안의 값이 같으면 이름을 지정해주지 않아도 됨

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
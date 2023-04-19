package com.springboot.springbootidu.web;

import com.springboot.springbootidu.domain.posts.Posts;
import com.springboot.springbootidu.domain.posts.PostsRepository;
import com.springboot.springbootidu.web.dto.PostsSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/idu")
public class IndexController {
    @Autowired
    private PostsRepository postsRepository;

    @PostMapping("/test")
    public String test(@RequestParam String title, @RequestParam String content, @RequestParam String author){
        System.out.println("page = test");
        System.out.println("title = " + title);
        System.out.println("content = " + content);
        System.out.println("author = " + author);
        Posts savePosts = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());
        postsRepository.save(savePosts);
        return "redirect:/";
    }

    @RequestMapping(value="/posts/save", method = RequestMethod.GET)
    public String hellopage(PostsSaveRequestDto dto) {
        System.out.println("dto---------------------------"+dto);

        return "idu/create-content";
    }
    @GetMapping("/")
    public void index(){
        System.out.println("postsRepository = " + postsRepository);;
    }
}
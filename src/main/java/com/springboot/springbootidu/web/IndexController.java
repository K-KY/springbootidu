package com.springboot.springbootidu.web;

import com.springboot.springbootidu.domain.posts.PostsRepository;
import com.springboot.springbootidu.service.posts.PostsService;
import com.springboot.springbootidu.web.dto.PostsSaveRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    private final PostsRepository postsRepository;
    private final PostsService postsService;

    public IndexController(PostsRepository postsRepository, PostsService postsService) {
        this.postsRepository = postsRepository;
        this.postsService = postsService;
    }

    @PostMapping("/test")
    public String test(@RequestParam String title, @RequestParam String content, @RequestParam String author){
        System.out.println("page = test");
        System.out.println("title = " + title);
        System.out.println("content = " + content);
        System.out.println("author = " + author);
/*
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder() .title(title)
                .content(content)
                .author(author)
                .build();
*/
//        Posts savePosts = postsRepository.save(Posts.builder()
//                .title(title)
//                .content(content)
//                .author(author)
//                .build());
//        postsRepository.save(savePosts);
        return "redirect:/";
    }

    @RequestMapping(value="/posts/save", method = RequestMethod.GET)
    public String hellopage(PostsSaveRequestDto dto) {
        System.out.println("dto---------------------------"+dto);

        return "idu/create-content";
    }

    @GetMapping("/")
    public String  index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }
}
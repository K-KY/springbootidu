package com.springboot.springbootidu.web;

import com.springboot.springbootidu.config.auth.LoginUser;
import com.springboot.springbootidu.config.auth.dto.SessionUser;
import com.springboot.springbootidu.domain.posts.PostsRepository;
import com.springboot.springbootidu.service.posts.PostsService;
import com.springboot.springbootidu.web.dto.PostsResponseDto;
import com.springboot.springbootidu.web.dto.PostsSaveRequestDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    private final PostsRepository postsRepository;
    private final HttpSession httpSession;
    private final PostsService postsService;

    public IndexController(PostsRepository postsRepository, HttpSession httpSession, PostsService postsService) {
        this.postsRepository = postsRepository;
        this.httpSession = httpSession;
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

    @GetMapping("/posts/save")
    public String hellopage(PostsSaveRequestDto dto) {
        System.out.println("dto---------------------------"+dto);

        return "idu/create-content";
    }

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        //@LoginUser SessionUser user

        model.addAttribute("posts", postsService.findAllDesc());

        if(user !=null){
            System.out.println("user.getName=" + user.getName());
            model.addAttribute("userName", user.getName());
        }
//        postsRepository.findAll();
//        이렇게 해도 동작 하는데 새로운 메소드를 만들어서 데이터를 받아온 이유가 뭘까{
//        데이터를 가져오는 코드가 컨트롤러와 뷰 사이에 직접 존재하게 되면 유지보수가 어렵게 되고 코드가 복잡해진다.}
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
            return "idu/posts-update";
    }

}

/**
 * @LoginUser SessionUser user
 * 기존에 (user)httpSession.getAttribute("user") 로 가져오던 세션 정보갑이 개선되었다
 * 이제는 어느 컨트롤러든 @LoginUser 만사용하면 세션 정보를 가져 올 수 있다
 */
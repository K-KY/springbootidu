package com.springboot.springbootidu.web;


import com.springboot.springbootidu.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController//1
public class HelloController {
    @GetMapping("/hello")//2
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto(
       /*3*/@RequestParam("name")
            String name,
            @RequestParam("amount")
            int amount) {

        return new HelloResponseDto(name,amount);
    }
}

/**
 * 1. RestController
 * 컨트롤러를 JSON 을 반환하는 컨트롤러로 만든다.
 * 2. @GetMapping
 * HTTP Method 인 Get 요청을 받을 수 있는 API 를 만든다.
 * 3. RequestParam
 *
 */
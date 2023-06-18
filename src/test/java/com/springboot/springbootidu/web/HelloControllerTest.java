package com.springboot.springbootidu.web;


import com.springboot.springbootidu.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)//1
@WebMvcTest(controllers = HelloController.class,
excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = SecurityConfig.class)
})//2
@ActiveProfiles("test")
public class HelloControllerTest {
    @Autowired//3
    private MockMvc mvc;//4

    @WithMockUser(roles = "USER")
    @Test
    public void helloTest() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello")).andExpect(content().string(hello)).andExpect(status().isOk());//5,6,7

    }
    @WithMockUser(roles = "USER")
    @Test
    public void helloDtoTest() throws Exception {
        String name ="hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name",name).param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
/**
 * 1.ExtendWith(SpringExtension.class)
 * 테스트 할 때 JUnit 외에 다른 실행자를 실행시킨다
 * 여기서는 SpringExtension 스프링 실행자를 사용
 *
 * 2.WebMvcTest
 * 스프링 어노테이션 중 Web(Spring MVC)  에 집중 할 수 있는 어노테이션
 * 선언 할 경우 @Controller @ControllerAdvice  등을 사용
 * @Service , @Component, @Repository 사용 불가
 *
 * 3.Autowired
 * 스프링이 관리하는 빈을 주입
 *
 * 4.private MockMvc mvc
 * 웹 API 를 테스트 할 때 사용한다
 * HTTP GET, POST 등에 대한 API 테스트 가능
 *
 * 5.mvc.perform(get("/hello"))
 * MockMVC 를 통해 /hello 주소로 HTTP GET 요청을 한다
 *
 * 6.andExpect(status().isOk())
 * mvc.perform 의 결과를 검증
 *
 * 7.andExpect(content().string(hello))
 * ''
 * 응답 본 문의 내용을 검증한다
 *Controller 에서 "hello" 를 리턴하는데 이 값이 맞는지 검증한다
 *
 * 8. param
 * API 테스트 할 때 사용될 요청 파라미터를 설정한다.
 * 값은 String 만 허용된다.
 * String 이 아닌 값은 String 으로 변환 해야 가능하다.
 */
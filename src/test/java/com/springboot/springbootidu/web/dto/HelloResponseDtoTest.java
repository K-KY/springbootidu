package com.springboot.springbootidu.web.dto;


import com.springboot.springbootidu.SpringbootiduApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = SpringbootiduApplication.class)
@ActiveProfiles("test")
public class HelloResponseDtoTest {
    @Test
    public void lombokTest(){
        //given
        String name = "test";
        int amount = 1000;
        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);
        //then
        assertThat(dto.getName()).isEqualTo(name);//1,2
        assertThat(dto.getAmount()).isEqualTo(amount);

    }
}
/**
 * 1. assertThat
 * 테스트 검증 라이브러리
 * 검증하고싶은 대상을 메소드 인자로 받는다
 *
 * 2. isEqualTo
 * assertThat 에 있는 값과 isEqualTo 의 값을 비교해서 같을 때 만 성공
 */
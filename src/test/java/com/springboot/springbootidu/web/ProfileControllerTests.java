package com.springboot.springbootidu.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProfileControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void profileAuthLessCall() throws Exception{
        String expected = "default";
        ResponseEntity<String> response = restTemplate.getForEntity("/profile",String.class);
        //restTemplate 를 사용하여 /profile 엔드 포인트에서 HTTP GET 요청을 보낸다.
        //응답의 본문을 String.class 로 변환하도록 Type 를 지정
        // 응답의 정보를 ResponseEntity 객체인 response 에 저장

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).isEqualTo(expected);
    }

}
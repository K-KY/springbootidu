package com.springboot.springbootidu.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter//1
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;

}

/**
 * 1. Getter
 * 선언된 모든 필드의 get 메소드를 생성한다
 *
 * 2. RequiredArgConstructor
 * 선언된 모든 final 필드가 포함된 생성자를 생성한다
 * final 이 아닌건 생성하지 않는다.
 */
package com.springboot.springbootidu.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "일반 사용자");
    private final String key;
    private final String title;
}
/**
 * 스프링 시큐리티 권한 코드에는 항상 ROLE_ 이 앞에 있어야 한다
 * 그래서 코드별 키 값을 ROLE_xxx 로 지정
 */

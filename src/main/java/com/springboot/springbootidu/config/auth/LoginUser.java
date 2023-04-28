package com.springboot.springbootidu.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)//1
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {//2
}

/**
 * 1. @Target(ElementType.PARAMETER)
 * 이 이 어노테이션이 생성 될 수 있는 위치를 지정한다.
 * PARAMETER 로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다
 *
 * 1. @interface
 * 이 파일을 어노테이션 클래스로 지정한다
 * LoginUser 라는 어노테이션이 생성된거임
 */

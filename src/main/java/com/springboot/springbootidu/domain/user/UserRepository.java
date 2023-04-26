package com.springboot.springbootidu.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);//1
}

/**
 * 1. findByEmail
 * 소셜 로그인으로 반환되는 값 중 Email 로 이미 생성된 사용자인지 처음 가입하는 사용자이지 판단하는 메소드
 */
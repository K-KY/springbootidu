package com.springboot.springbootidu.domain.user;

import com.springboot.springbootidu.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Users(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public Users update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
/**
 * Enumerated(EnumType.STRING)
 * JPA 로 데이터베이스에 저장 할 때 Enum 값을 어떤 형태로 저장할지 결정
 * 기본은 int
 * 숫자로 저장되면 데이터 베이스로 확인 할 때 그 값이 무슨 코드를 의미하는지 모름
 * 그래서 String 으로 저장
 */
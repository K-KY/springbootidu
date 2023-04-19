package com.springboot.springbootidu.domain.posts;

import com.springboot.springbootidu.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter//6
@NoArgsConstructor//5
@Entity//1
public class Posts extends BaseEntity {
    @Id//2
    @GeneratedValue(strategy = GenerationType.IDENTITY)//3
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;


    @Builder//7
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

/**
 * 1. @Entity
 * 테이블과 링크 될 클래스임을 나타낸다
 * 기본값으로 카멜케이스 언더스코어 네이밍으로 이름을 만든다
 * 예시) SampleEntity -> sample_entity
 * 2. @Id
 * 해당 테이블의 Primary Ket 필드를 나타낸다
 * 3. @GeneratedValue
 * Primary Key 의 생성 규칙을 나타낸다
 * GenerationValue.IDENTITY 옵션을 추가하면  auto_increment 가 된다
 * 4. @Column
 * 테이블 칼럼을 나타내고 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 된다
 * 사용 이유 : 기본값 외에 추가로 변경이 필요할 때
 * 5. @NoArgsConstructor
 * 가본 생성자 자동 추가
 * public Posts(){} 와 같음
 * 6. @Getter
 * 클래스 내 모든 필드의 Getter 메소드 생성
 * 7.@ Builder
 * 해당 클래스의 빌더 패턴 클래스 생성
 * 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
 */
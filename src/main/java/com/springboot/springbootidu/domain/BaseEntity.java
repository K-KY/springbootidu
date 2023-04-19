package com.springboot.springbootidu.domain;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass//1
@EntityListeners(AuditingEntityListener.class)//2
public abstract class BaseEntity {
    @CreatedDate//3
    private LocalDateTime createDate;
    @LastModifiedDate//4
    private LocalDateTime modifiedDate;
}

/**
 * 1. MappedSuperClass
 * JPA Entity 클래스 들이 BaseEntity 를 상속할경우 필드들도 칼럼으로 인식한다
 *
 * 2. EntityListener(AuditingListener)
 * BaseEntity 클래스에 Auditing 기능을 포함시킨다.
 *
 * 3.@CreateDate
 * Entity 가 생성 되어 저장 될 때 시간이 자동 저장된다.
 *
 * 4. @ListModifiedDate
 * 조회한 Entity 의 값을 변경 할 때 시간이 자동 저장된다.
 */
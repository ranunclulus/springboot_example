package com.example.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@MappedSuperclass // 다른 엔티티들이 상속받기 위한 기본 클래스
// 여러 엔티티가 공유하는 속성을 모으기 위한 상위 엔티티임을 나타내는 어노테이션
// 엔티티의 변화를 지켜볼 클래스 정의
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private Instant createAt;

    @LastModifiedDate
    @Column(updatable = true)
    private Instant updatedAt;
}

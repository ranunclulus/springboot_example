package com.example.auth.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // TODO
    // 1. 사용자 계정 이름으로 사용자 정보 회수
    Optional<UserEntity> findByUsername(String username);
    // 2. 사용자 계정 이름을 가진 사용자 정보 존재하는지 판단
    boolean existsByUsername(String username);
}

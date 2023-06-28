package com.example.contents;

import com.example.contents.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    // 새 UserEntity를 데이터베이스에 추가 성공
    @Test
    @DisplayName("새로운 UserEntity 추가")
    public void testSaveNew() {
        // given: 테스트가 진행되기 위한 전제 조건을 준비하는 구간 -> 새로운 UserEntity 준비
        String username = "huisu";
        UserEntity user = new UserEntity();
        user.setUsername(username);

        // when: 테스트 하고 싶은 실제 기능을 작성하는 구간
        user = userRepository.save(user);

        // then: 실행한 결과가 기대한 것과 같은지릘 검증하는 구간
        // 1. 새로 반환받은 user의 id는 null이 아님
        assertNotNull(user.getId());
        // 2. 새로 반환받은 user의 username은 넣은 값과 동일
        assertEquals(username, user.getUsername());
    }

    @Test
    @DisplayName("새 UserEntity를 데이터베이스에 추가 실패")
    public void testSaveNewFail() {
        // given username을 가지고 UserEntity를 먼저 save
        String username = "huisu";
        UserEntity userGiven = new UserEntity();
        userGiven.setUsername(username);
        userRepository.save(userGiven);

        // when 같은 username을 가진 UserEntity save 시도
        UserEntity user = new UserEntity();
        user.setUsername(username);

        // when-then 예외 발생
        assertThrows(Exception.class, () -> userRepository.save(user));
    }

    @Test
    @DisplayName("username으로 UserEntity 찾기")
    public void testFindByUsername() {
        // given: 검색할 유저
        UserEntity userGiven = new UserEntity();
        String username = "huisu";
        userGiven.setUsername(username);
        userRepository.save(userGiven);

        // when: UserRepository에 findByUsername
        Optional<UserEntity> user = userRepository.findByUsername(username);

        // then: Optional.isPresent(), username == username
        assertTrue(user.isPresent());
        assertTrue(user.get().getUsername() == username);
    }
}
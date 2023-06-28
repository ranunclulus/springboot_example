package com.example.contents;

import com.example.contents.dto.UserDto;
import com.example.contents.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
// Mocking 가짜 객체를 만들어 주는 클래스
public class UserServiceTests {
    // Mock: 인터페이스로 메소드를 가지고 있지만 내부 동작은 정의되어 있지 않은 모조 객체
    @Mock
    private UserRepository userRepository;

    // IngectMocks:
    @InjectMocks
    private UserService userService;

    // UserDto를 입력받아 UserDto(id != null)를 반환
    @Test
    @DisplayName("UserDto로 createUser")
    public void testCreateUser() {
        // given:
        // 1. UserRepository가 전달받을 UserEntity 정의
        String username = "huisu";
        UserEntity userEntityIn = new UserEntity();
        userEntityIn.setUsername(username);

        // 2. UserRepository가 반환할 UserEntity 정의
        Long userId = 1L;
        UserEntity userEntityOut = new UserEntity();
        userEntityOut.setId(userId);
        userEntityOut.setUsername(username);

        // 3. UserRepository.save()의 기능을 따라 하도록 설정
        // userRepository는 아래와 같이 기능할 것이다라고 가정
        when(userRepository.save(userEntityIn))
                .thenReturn(userEntityOut);
        when(userRepository.existsByUsername(username))
                .thenReturn(false);

        // when:
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        UserDto result = userService.createUser(userDto);

        // then:

        assertEquals(userId, result.getId());
        assertEquals(username, result.getUsername());
    }
}

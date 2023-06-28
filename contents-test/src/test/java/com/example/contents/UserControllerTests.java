package com.example.contents;

import com.example.contents.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    // HTTP 요청이 왔음을 가정해 주는 객체
    private MockMvc mockMvc;


    // 단위 테스트 이전에 mockMvc 가 초기화
    @BeforeEach
    public void beforeEach() {
        // 컨트롤러로 userController 등록
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        // UserDto -> JSON
        // JSON -> UserDto
        // mock에서는 직렬화 역직렬화가 자동으로 안 됨
    }

    @Test
    @DisplayName("UserDto를 나타내는 JSON 요청을 보내면 id가 null이 아닌 UserDto JSON 응답")
    public void testCreate() throws Exception {
        // given
        // 1. userService.createUser에 전달할 UserDto 준비
        String username = "huisu";
        UserDto requestDto = new UserDto();
        requestDto.setUsername(username);
        // 2. userService.createUser가 반환할 UserDto 준비
        Long userId = 1L;
        UserDto responseDto = new UserDto();
        requestDto.setUsername(responseDto.getUsername());
        responseDto.setId(userId);
        // 3. userService.creatUser의 동작 가정
        when(userService.createUser(requestDto))
                .thenReturn(responseDto);

        // when
        // perform: HTTP 요청을 보낸 것울 시뮬레이션하여 userController에게
        ResultActions result = mockMvc.perform(
                // 요청의 형태 (Body ...)를 빌더처럼 정의
                // requestDto의 JSON일 것이야
                post("/users")
                        .content(JsonUtil.toJson(requestDto))
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpectAll(
               status().is2xxSuccessful(), // 성공적
                content().contentType(MediaType.APPLICATION_JSON), // 응답이 JSON 형태
                jsonPath("$.username", is(username)), // username은 요청한 값 그대로
                jsonPath("$.id", notNullValue()) // id는 null이 아닌 값
        );
    }
}

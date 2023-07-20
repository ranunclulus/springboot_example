package com.example.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTests {
    @InjectMocks
    private ArticleController articleController;
    @Mock
    private ArticleService articleService;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @Test
    public void mockMvcIsNotNull() {
        assertThat(articleController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void queryArticle() throws Exception {
        // given
        String url = "/articles?title=test";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle("contains test");
        when(articleService.findByTitle("test"))
                .thenReturn(Collections.singletonList(articleDto));

        // when
        ResultActions resultActions = mockMvc.perform(get(url));

        // then
        resultActions.andExpectAll(
                status().is2xxSuccessful(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$[0].title", containsString("test"))
        );
    }
}

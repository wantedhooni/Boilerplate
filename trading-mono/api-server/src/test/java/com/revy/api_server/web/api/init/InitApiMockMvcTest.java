package com.revy.api_server.web.api.init;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("InitApi MockMvc 슬라이스 테스트")
class InitApiMockMvcTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new InitApi()).build();
    }

    @Test
    @DisplayName("init 엔드포인트는 문자열을 반환한다")
    void init_returnsString() throws Exception {
        mockMvc.perform(get("/init"))
                .andExpect(status().isOk())
                .andExpect(content().string("init"));
    }
}

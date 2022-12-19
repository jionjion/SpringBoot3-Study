package top.jionjion.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

/**
 * 测试异步处理任务
 *
 * @author Jion
 */
@SpringBootTest
class AsyncBusinessControllerTest {

    @Autowired
    AsyncBusinessController asyncBusinessController;

    MockMvc mockMvc;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(asyncBusinessController).build();
    }

    @Test
    void asyncDoSomeThings() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/async?things=A,B,C").accept(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
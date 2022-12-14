package top.jionjion.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import top.jionjion.web.bean.User;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.Date;

/**
 * SpringMVC 单元测试
 *
 * @author Jion
 */
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    MockMvc mockMvc;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void userList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/users").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void userSave() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("jionjion");
        user.setPassword("123456");
        user.setAddress("ShangHai");
        user.setBirthday(new Date());
        // json 请求体
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/user").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void userGet() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/users/1").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void userUpdate() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("username", Collections.singletonList("jion"));
        params.put("password", Collections.singletonList("123456"));

        // json 请求体
        this.mockMvc.perform(MockMvcRequestBuilders.put("/user/users/{id}", 1).contentType(MediaType.MULTIPART_FORM_DATA_VALUE).params(params).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void userDelete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/user/users/{id}", 1).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void userGetByUsername() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/users/username/{username}", "jion").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void uploadPhoto() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setPassword("password");
        user.setAddress("ShangHai");
        user.setBirthday(new Date());

        // json 文本
        MockMultipartFile multipartJson = new MockMultipartFile("user", "", "application/json", objectMapper.writeValueAsString(user).getBytes());

        // 文件
        MockMultipartFile multipartFile = new MockMultipartFile("file", "jion.jpg", MediaType.IMAGE_JPEG_VALUE, new FileInputStream("W:\\SpringBoot3-Study\\web\\src\\test\\resources\\static\\jion.jpg"));

        // cookie
        Cookie cookie = new Cookie("token", "1234567890");

        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/user/users/photo").file(multipartFile).file(multipartJson).cookie(cookie)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
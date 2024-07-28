package ru.mironov.securityjwt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    public void getUserGreetings_whenNotAuthorized_thenStatus403AndNoMessage() throws Exception {
        String result = mockMvc.perform(get("/demo")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertThat(result).isEmpty();
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void getUserGreetings_whenNotAdmin_thenStatus200AndGreetingsMessage() throws Exception {
        String result = mockMvc.perform(get("/demo")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertThat(result).isEqualTo("Hello, User!");
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void getUserGreetings_whenAdmin_thenStatus200AndGreetingsMessage() throws Exception {
        String result = mockMvc.perform(get("/demo")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertThat(result).isEqualTo("Hello, User!");
    }


    @Test
    @WithAnonymousUser
    public void getAdminGreetings_whenNotAuthorized_thenStatus403AndNoMessage() throws Exception {
        String result = mockMvc.perform(get("/demo/admin")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertThat(result).isEmpty();
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void getAdminGreetings_whenNotAdmin_thenStatus403AndNoMessage() throws Exception {
        String result = mockMvc.perform(get("/demo/admin")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertThat(result).isEmpty();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void getAdminGreetings_whenAdmin_thenStatus200AndGreetingsMessage() throws Exception {
        String result = mockMvc.perform(get("/demo/admin")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertThat(result).isEqualTo("Hello, Admin!");
    }
}
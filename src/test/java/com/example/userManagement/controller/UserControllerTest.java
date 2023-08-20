package com.example.userManagement.controller;

import com.example.userManagement.entity.UserEntity;
import com.example.userManagement.service.UserService;
import com.example.userManagement.service.dto.UserUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @SpyBean
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {

        when(userService.getUser(anyString())).thenReturn(new UserEntity());
        doNothing().when(userService).updateUser(anyString(), any());
        doNothing().when(userService).deleteUser(anyString());
    }

    @Test
    @WithMockUser(username = "user123", roles = "USER")
    void getUser() throws Exception {
        mockMvc.perform(get("/api/user/{username}", "user123"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetUserNotAuthenticated() throws Exception {
        String username = "user123";
        mockMvc.perform(get("/api/users/{username}", username))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user123", roles = "ADMIN")
    void getUserNotAuthorized() throws Exception {
        mockMvc.perform(get("/api/user/{username}", "user123"))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    void updateUser() throws Exception {
        mockMvc.perform(put("/api/user/update/{username}", "user123")
                        .content(asJsonString(userUpdateRequestDto()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateUserNotAuthenticated() throws Exception {
        mockMvc.perform(put("/api/user/update/{username}", "user123")
                        .content(asJsonString(userUpdateRequestDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/user/delete/{username}", "user123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteUserNotAuthenticated() throws Exception {
        mockMvc.perform(delete("/api/user/delete/{username}", "user123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    void deleteUserNotAuthorize() throws Exception {
        mockMvc.perform(delete("/api/user/delete/{username}", "user123")
                        .contentType(MediaType.APPLICATION_JSON)
                .with(user("admin").roles("USER")))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    private UserUpdateRequestDto userUpdateRequestDto() {
        return UserUpdateRequestDto.builder()
                .firstName("TEst")
                .lastName("Test22")
                .build();
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
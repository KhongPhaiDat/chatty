package com.newbiebe.chatty.userTest;

import com.newbiebe.chatty.entity.ChatUser;
import com.newbiebe.chatty.controller.ChatUserController;
import com.newbiebe.chatty.service.ChatUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ChatUserController.class)
public class ChatUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatUserService chatUserService;

    @Test
    void createChatUser_should_respond_OK_and_return_JSON_with_right_format() throws Exception {
        ChatUser chatUser = new ChatUser("Joshua Bloch");
        chatUser.setUserId(1L); // Assuming the ID is set after saving

        given(chatUserService.saveChatUser(any(ChatUser.class))).willReturn(chatUser);

        mockMvc.perform(post("/api/chatUsers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Joshua Bloch\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.name", is("Joshua Bloch")));
    }

    @Test
    void getAllChatUser_should_respond_OK_and_return_all_users() throws Exception {
        ChatUser chatUser = new ChatUser("Joshua Bloch");
        chatUser.setUserId(1L);
        ChatUser chatUser2 = new ChatUser("Joshua Bloch 2");
        chatUser.setUserId(2L);

        List<ChatUser> chatUsers = Arrays.asList(chatUser, chatUser2);

        given(chatUserService.getAllChatUsers()).willReturn(chatUsers);

        mockMvc.perform(get("/api/chatUsers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getChatUserById_should_respond_OK_and_return_user() throws Exception {
        ChatUser chatUser = new ChatUser("Joshua Bloch");
        chatUser.setUserId(1L);

        given(chatUserService.getChatUserById(1L)).willReturn(chatUser);

        mockMvc.perform(get("/api/chatUsers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.name", is("Joshua Bloch")));
    }

    @Test
    void updateChatUserById_should_respond_OK_and_return_user() throws Exception {
        ChatUser updatedUser = new ChatUser("Joshua Bloch v2");
        updatedUser.setUserId(1L);

        given(chatUserService.updateChatUser(any(Long.class), any(ChatUser.class))).willReturn(updatedUser);

        mockMvc.perform(put("/api/chatUsers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Joshua Bloch v2\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.name", is("Joshua Bloch v2")));
    }

    @Test
    void deleteChatUserById_should_respond_OK_and_return_boolean() throws Exception {
        long UserId = 1L;

        given(chatUserService.deleteChatUser(any(Long.class))).willReturn(true);

        mockMvc.perform(delete("/api/chatUsers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(true)));


    }
}


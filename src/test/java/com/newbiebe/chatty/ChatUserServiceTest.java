package com.newbiebe.chatty;

import com.newbiebe.chatty.entity.ChatUser;
import com.newbiebe.chatty.repository.ChatUserRepository;
import com.newbiebe.chatty.service.impl.ChatUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class ChatUserServiceTest {
    @Mock
    private ChatUserRepository chatUserRepository;

    @InjectMocks
    private ChatUserServiceImpl chatUserService;

    @BeforeEach
    void setUp() {
        // Initialize mocks annotated with @Mock, and inject them into the @InjectMocks instance
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_user_should_return_saved_user() {
        // Given
        ChatUser chatUser = new ChatUser("Joshua Bloch");
        given(chatUserRepository.save(chatUser)).willReturn(chatUser);

        // When
        ChatUser savedChatUser = chatUserService.saveChatUser(chatUser);

        // Then
        assertThat(savedChatUser).isNotNull();
        assertThat(savedChatUser.getName()).isEqualTo("Joshua Bloch");
    }

    @Test
    void get_allChatUsers_should_return_allUsers() {
        // Given
        ChatUser chatUser = new ChatUser("Joshua Bloch");
        ChatUser chatUser2 = new ChatUser("Joshua Bloch 2");

        List<ChatUser> chatUsers = Arrays.asList(chatUser, chatUser2);

        given(chatUserRepository.findAll()).willReturn(chatUsers);

        // When
        List<ChatUser> savedAllChatUsers = chatUserService.getAllChatUsers();

        // Then

        assertEquals(chatUsers.get(0).getName(), savedAllChatUsers.get(0).getName());
        assertEquals(chatUsers.get(1).getName(), savedAllChatUsers.get(1).getName());
    }

    @Test
    void get_ChatUser_by_id_should_return_User() {
        // Given
        ChatUser chatUser = new ChatUser("Joshua Bloch");
        chatUser.setUserId(1L);
        Optional<ChatUser> optionalChatUser = Optional.of(chatUser);

        given(chatUserRepository.findById(1L)).willReturn(optionalChatUser);

        // When
        ChatUser getChatUser = chatUserService.getChatUserById(1L);

        // Then
        assertThat(getChatUser.getName()).isEqualTo("Joshua Bloch");
    }

    @Test
    void update_ChatUser_by_id_should_return_updatedUser() {
        // Given
        ChatUser chatUser = new ChatUser("Old");
        chatUser.setUserId(1L);

        ChatUser newChatUser = new ChatUser("New");
        chatUser.setUserId(1L);

        Long userId = 1L;

        given(chatUserRepository.findById(userId)).willReturn(Optional.of(chatUser));

        given(chatUserRepository.save(newChatUser)).willReturn(newChatUser);

        // When
        ChatUser updateChatUser = chatUserService.updateChatUser(1L, newChatUser);

        // Then
        assertThat(getChatUser.getName()).isEqualTo("Joshua Bloch");
    }
}

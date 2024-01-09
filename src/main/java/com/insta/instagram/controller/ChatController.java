package com.insta.instagram.controller;

import com.insta.instagram.exception.ChatException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Chat;
import com.insta.instagram.model.User;
import com.insta.instagram.request.GroupChatRequest;
import com.insta.instagram.request.SingleChatRequest;
import com.insta.instagram.response.MessageResponse;
import com.insta.instagram.service.ChatService;
import com.insta.instagram.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    private ChatService chatService;
    private UserService userService;
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }
    @PostMapping("/single")
    public ResponseEntity<Chat> createChatHandler (@RequestBody SingleChatRequest singleChatRequest, @RequestHeader("Authorization")String token) throws UserException {
        User reqUser = userService.findUserProfile(token);
        Chat chat = chatService.createChat(reqUser, singleChatRequest.getUserId());
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupHandler (@RequestBody GroupChatRequest groupChatRequest, @RequestHeader("Authorization")String token) throws UserException {
        User reqUser = userService.findUserProfile(token);
        Chat chat = chatService.createGroup(groupChatRequest, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler (@PathVariable Integer chatId, @RequestHeader("Authorization")String token) throws ChatException {
        Chat chat = chatService.findChatById(chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Chat>> findAllUserChatsHandler (@RequestHeader("Authorization")String token) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(token);
        List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }
    @PutMapping("/{chatId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupHandler (@PathVariable Integer userId, @PathVariable Integer chatId, @RequestHeader("Authorization")String token) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(token);
        Chat chat = chatService.addUserToGroup(userId,chatId,reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @PutMapping("/{chatId}/remove/{userId}")
    public ResponseEntity<Chat> removeUserToGroupHandler (@PathVariable Integer userId, @PathVariable Integer chatId, @RequestHeader("Authorization")String token) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(token);
        Chat chat = chatService.removeFromGroup(userId,chatId,reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @DeleteMapping("/remove/{chatId}")
    public ResponseEntity<Chat> deleteChatHandler (@PathVariable Integer chatId, @RequestHeader("Authorization")String token) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(token);
        Chat chat = chatService.deleteChat(chatId,reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
}

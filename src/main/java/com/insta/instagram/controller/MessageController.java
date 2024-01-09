package com.insta.instagram.controller;

import com.insta.instagram.exception.ChatException;
import com.insta.instagram.exception.MessageException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Message;
import com.insta.instagram.model.User;
import com.insta.instagram.request.SendMessageRequest;
import com.insta.instagram.service.ChatService;
import com.insta.instagram.service.MessageService;
import com.insta.instagram.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private MessageService messageService;
    private UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest request, @RequestHeader("Authorization")String token) throws UserException, ChatException {
        System.out.print(request);
        User user = userService.findUserProfile(token);
        request.setUserId(user.getId());

        Message message = messageService.sendMessage(request);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getChatMessageHandler(@PathVariable Integer chatId, @RequestHeader("Authorization")String token) throws UserException, ChatException {
        User user = userService.findUserProfile(token);
        List<Message> messages = messageService.getChatMessages(chatId, user);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Message> deleteMessageHandler(@PathVariable Integer messageId, @RequestHeader("Authorization")String token) throws UserException, ChatException, MessageException {
        User user = userService.findUserProfile(token);
        Message message = messageService.deleteMessage(messageId,user);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

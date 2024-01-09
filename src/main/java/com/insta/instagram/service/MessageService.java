package com.insta.instagram.service;

import com.insta.instagram.exception.ChatException;
import com.insta.instagram.exception.MessageException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Message;
import com.insta.instagram.model.User;
import com.insta.instagram.request.SendMessageRequest;

import java.util.List;

public interface MessageService {
    public Message sendMessage(SendMessageRequest req) throws UserException, ChatException;
    public List<Message> getChatMessages(Integer chatId, User reqUser) throws ChatException, UserException;
    public Message findMessageById(Integer messageId) throws MessageException;
    public Message deleteMessage(Integer messageId, User reqUser) throws MessageException, UserException;
}

package com.insta.instagram.service;

import com.insta.instagram.exception.ChatException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Chat;
import com.insta.instagram.model.User;
import com.insta.instagram.request.GroupChatRequest;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, Integer userId2) throws UserException;
    public Chat findChatById(Integer chatId) throws ChatException;
    public List<Chat> findAllChatByUserId(Integer userId) throws UserException;
    public Chat createGroup(GroupChatRequest req, User reqUserId) throws UserException;
    public Chat addUserToGroup(Integer userId, Integer chatId, User reqUser) throws UserException, ChatException;
    public Chat renameGroup(Integer chatId, String groupName, User reqUser) throws ChatException, UserException;
    public Chat removeFromGroup(Integer chatId, Integer userId, User reqUser) throws ChatException, UserException;
    public Chat deleteChat(Integer chatId, User reqUser) throws ChatException, UserException;
}

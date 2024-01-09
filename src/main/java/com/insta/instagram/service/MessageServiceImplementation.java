package com.insta.instagram.service;

import com.insta.instagram.exception.ChatException;
import com.insta.instagram.exception.MessageException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Chat;
import com.insta.instagram.model.Message;
import com.insta.instagram.model.User;
import com.insta.instagram.repository.MessageRepository;
import com.insta.instagram.request.SendMessageRequest;
import com.insta.instagram.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImplementation implements MessageService{
    private MessageRepository messageRepository;
    private UserService userService;
    private ChatService chatService;

    public MessageServiceImplementation(MessageRepository messageRepository, UserService userService, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.chatService = chatService;
    }

    @Override
    public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {
        User user = userService.findUserById(req.getUserId());
        Chat chat = chatService.findChatById(req.getChatId());
        Message message = new Message();
        message.setChat(chat);
        message.setUser(user);
        message.setContent(req.getContent());
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
        return message;
    }

    @Override
    public List<Message> getChatMessages(Integer chatId, User reqUser) throws ChatException, UserException {
        Chat chat = chatService.findChatById(chatId);
        if(!chat.getUsers().contains(reqUser)){
            throw new UserException("You are not related to this chat");
        }
        List<Message> messages = messageRepository.findByChatId(chat.getId());
        return messages;
    }

    @Override
    public Message findMessageById(Integer messageId) throws MessageException {
        Optional<Message> opt = messageRepository.findById(messageId);
        if(opt.isPresent())
            return opt.get();
        throw new MessageException("Message not found with id " + messageId);
    }

    @Override
    public Message deleteMessage(Integer messageId, User reqUser) throws MessageException, UserException {
        Message message = findMessageById(messageId);
        if(message.getUser().getId().equals(reqUser.getId())) {
            messageRepository.deleteById(messageId);
        }
        throw new UserException("You can not delete another user's message");
    }
}

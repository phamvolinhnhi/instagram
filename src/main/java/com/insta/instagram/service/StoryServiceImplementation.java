package com.insta.instagram.service;

import com.insta.instagram.dto.UserDTO;
import com.insta.instagram.exception.StoryException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Story;
import com.insta.instagram.model.User;
import com.insta.instagram.repository.StoryRepository;
import com.insta.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class StoryServiceImplementation implements StoryService{
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Story createStory(Story story, Integer userId) throws UserException {
        User user = userService.findUserById(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUserImage(user.getImage());
        userDTO.setUsername(user.getUsername());
        story.setUser(userDTO);
        story.setTimeStamp(LocalDateTime.now());
        user.getStories().add(story);
        return storyRepository.save(story);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException {
        User user = userService.findUserById(userId);
        List<Story> stories = user.getStories();
        if(stories.size()==0)
            throw new StoryException("This user doesn't have any story");
        return stories;
    }
}

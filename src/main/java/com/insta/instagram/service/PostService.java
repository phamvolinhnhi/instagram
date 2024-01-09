package com.insta.instagram.service;

import com.insta.instagram.exception.PostException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Post;

import java.util.List;

public interface PostService {
    public Post createPost(Post post, Integer userId) throws UserException;
    public Post deletePost(Integer postId, Integer userId) throws UserException, PostException;
    public List<Post> findPostByUserId(Integer userId) throws UserException;
    public Post findPostById(Integer postId) throws PostException;
    public List<Post> findAllPostByUserIds(List<Integer> userIds) throws PostException, UserException;
    public String savedPost(Integer postId, Integer userId) throws PostException, UserException;
    public String unSavedPost(Integer postId, Integer userId) throws PostException, UserException;
    public Post likePost(Integer postId, Integer userId) throws UserException, PostException;
    public Post unLikePost(Integer postId, Integer userId) throws UserException, PostException;
}

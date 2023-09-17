package com.insta.instagram.controller;

import com.insta.instagram.exception.CommentException;
import com.insta.instagram.exception.PostException;
import com.insta.instagram.exception.UserException;
import com.insta.instagram.model.Comment;
import com.insta.instagram.model.User;
import com.insta.instagram.service.CommentService;
import com.insta.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @PostMapping("/create/{postId}")
    public ResponseEntity<Comment> createCommentHandler(@RequestBody Comment comment, @PathVariable Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        Comment createdComment = commentService.createComment(comment, postId, user.getId() );
        return new ResponseEntity<Comment>(createdComment, HttpStatus.OK);
    }
    @PutMapping("/like/{commentId}")
    public ResponseEntity<Comment> likeCommentHandler(@RequestHeader("Authorization") String token, @PathVariable Integer commentId) throws UserException, CommentException {
        User user = userService.findUserProfile(token);
        Comment comment = commentService.likeComment(commentId, user.getId());
        return new ResponseEntity<Comment>(comment,HttpStatus.OK);
    }
    @PutMapping("/unLike/{commentId}")
    public ResponseEntity<Comment> unLikeCommentHandler(@RequestHeader("Authorization") String token, @PathVariable Integer commentId) throws UserException, CommentException {
        User user = userService.findUserProfile(token);
        Comment comment = commentService.unLikeComment(commentId, user.getId());
        return new ResponseEntity<Comment>(comment,HttpStatus.OK);
    }
}

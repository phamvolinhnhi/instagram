package com.insta.instagram.repository;

import com.insta.instagram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p where p.user.id=?1")
    public List<Post> findByUserId(Integer userId);
    @Query("select p from Post p where p.user.id in :users order by p.createdAt DESC")
    public List<Post> findAllPostByUserIds(@Param("users") List<Integer> userIds);
}

package com.insta.instagram.repository;

import com.insta.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("Select u from User u where u.email=?1")
    public Optional<User> findByEmail(String email);
    @Query("Select u from User u where u.username=?1")
    public Optional<User> findByUsername(String username);
    @Query("Select u from User u where u.id in :users")
    public List<User> findAllUsersByUserIds(@Param("users") List<Integer> userIds);
    @Query("Select distinct u from User u where u.username like %:query% or u.email like %:query%")
    public List<User> findByQuery(@Param("query") String query);
}

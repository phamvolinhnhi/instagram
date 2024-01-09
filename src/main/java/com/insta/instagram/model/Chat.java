package com.insta.instagram.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String image;
    @Column(name = "is_group")
    private boolean isGroup;
    @JoinColumn(name = "created_by")
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private Set<User> users = new HashSet<>();
    @ManyToMany
    private Set<User> admins = new HashSet<>();
    @OneToMany
    private List<Message> messages = new ArrayList<>();
    public Chat() {

    }

    public Chat(Integer id, String name, String image, boolean isGroup, User createdBy, Set<User> users, Set<User> admins, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.isGroup = isGroup;
        this.createdBy = createdBy;
        this.users = users;
        this.admins = admins;
        this.messages = messages;
    }

    public Set<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<User> admins) {
        this.admins = admins;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}

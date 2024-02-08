package com.varun.LibraryManagment.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long UserId;
    @Column(name = "name")
    String name;
    @Column(name = "email") String email;

    public User(Long userId, String name, String email) {
        UserId = userId;
        this.name = name;
        this.email = email;
    }

    public User() {

    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.UserId = id;
    }

    public Long getId() {
        return UserId;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package com.e.sb;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private String id;

    private String username;
    private String email;
    private String password; // Remember to store hashed passwords for security

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password; // Remember to hash passwords
    }


    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", username='" + this.username + '\'' + ", email='" + this.email + '\'' + '}';
    }

    // Getters and setters
    public String getId() {
        return this.id;

    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}

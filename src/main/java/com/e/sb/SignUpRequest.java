package com.e.sb;

public class SignUpRequest {

    private String username;
    private String email;
    private String password;

    public SignUpRequest() {
    }

    public SignUpRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password; // Remember to hash passwords
    }

    @Override
    public String toString() {
        return "User{" + "username='" + this.username + '\'' + ", email='" + this.email + '\'' + '}';
    }

    // Getters and setters
    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
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

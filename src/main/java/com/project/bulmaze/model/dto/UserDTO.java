package com.project.bulmaze.model.dto;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private String username;
    private String email;
    private int score;
    private Long userProgress;

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getScore() {
        return score;
    }

    public UserDTO setScore(int score) {
        this.score = score;
        return this;
    }

    public Long getUserProgress() {
        return userProgress;
    }

    public UserDTO setUserProgress(Long userProgress) {
        this.userProgress = userProgress;
        return this;
    }
}

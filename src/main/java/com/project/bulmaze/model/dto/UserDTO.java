package com.project.bulmaze.model.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private String username;
    private String email;
    private int score;
    private Long userProgress;
    private List<GivenAnswerDTO> answers;
    private List<UserRoleDTO> roles;
    private boolean reviewSent;
    private Long time;

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

    public List<GivenAnswerDTO> getAnswers() {
        return answers;
    }

    public UserDTO setAnswers(List<GivenAnswerDTO> answers) {
        this.answers = answers;
        return this;
    }

    public List<UserRoleDTO> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<UserRoleDTO> roles) {
        this.roles = roles;
        return this;
    }

    public String allRoles() {
        StringBuilder sb = new StringBuilder();
        this.roles.forEach(r -> {
            sb.append(r.getRole().toString());
            sb.append("  ");
        });
        return sb.toString();
    }

    public boolean isReviewSent() {
        return reviewSent;
    }

    public UserDTO setReviewSent(boolean reviewSent) {
        this.reviewSent = reviewSent;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public UserDTO setTime(Long time) {
        this.time = time;
        return this;
    }

    public String getTimeFormatted() {
        long elapsedSeconds = this.time;
        long hours;
        long minutes;
        long seconds;

        hours = elapsedSeconds / 3600;
        minutes = (elapsedSeconds % 3600) / 60;
        seconds = elapsedSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}

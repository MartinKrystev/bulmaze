package com.project.bulmaze.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    @Size(min = 3, max = 20)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @Size(min = 3, max = 20)
    private String lastName;
    @Column
    private String country;
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20)
    private String username;
    @Column(nullable = false)
    private String password;
    @Email
    @NotBlank
    @Column
    private String email;
    @Column(nullable = false)
    private int score;
    @ManyToMany
    private List<SeasonEntity> seasons;
    @ManyToMany
    private List<AchievementEntity> achievements;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<QuestionEntity> answeredQuestions;
    @Column(name = "user_progress")
    private Long userProgress;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GivenAnswerEntity> givenAnswers;
    @Column(name = "review_sent")
    private boolean reviewSent;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getScore() {
        return score;
    }

    public UserEntity setScore(int score) {
        this.score = score;
        return this;
    }

    public List<SeasonEntity> getSeasons() {
        return seasons;
    }

    public UserEntity setSeasons(List<SeasonEntity> seasons) {
        this.seasons = seasons;
        return this;
    }

    public List<AchievementEntity> getAchievements() {
        return achievements;
    }

    public UserEntity setAchievements(List<AchievementEntity> achievements) {
        this.achievements = achievements;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity addRole(UserRoleEntity role) {
        this.roles.add(role);
        return this;
    }

    public Long getUserProgress() {
        return userProgress;
    }

    public UserEntity setUserProgress(Long userProgress) {
        this.userProgress = userProgress;
        return this;
    }

    public List<QuestionEntity> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public UserEntity setAnsweredQuestions(List<QuestionEntity> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
        return this;
    }

    public UserEntity addAnsweredQuestion(QuestionEntity question) {
        this.answeredQuestions.add(question);
        return this;
    }

    public List<GivenAnswerEntity> getGivenAnswers() {
        return givenAnswers;
    }

    public UserEntity setGivenAnswers(List<GivenAnswerEntity> givenAnswers) {
        this.givenAnswers = givenAnswers;
        return this;
    }

    public UserEntity addGivenAnswer(GivenAnswerEntity givenAnswer) {
        this.givenAnswers.add(givenAnswer);
        return this;
    }

    public boolean isReviewSent() {
        return reviewSent;
    }

    public UserEntity setReviewSent(boolean reviewSent) {
        this.reviewSent = reviewSent;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", username='" + username + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : "[N/A]") + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                ", seasons=" + seasons +
                ", achievements=" + achievements +
                ", roles=" + roles +
                '}';
    }
}

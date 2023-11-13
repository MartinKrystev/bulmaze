package com.project.bulmaze.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 50)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 2, max = 5000)
    private String description;
    @Column(nullable = false)
    @Size(min = 2, max = 50)
    private String ask;
    @Column(nullable = false, name = "image_url")
    @NotBlank
    private String imageUrl;
    @OneToOne
    private AnswerEntity answer;
    @OneToOne
    private ClueEntity clue;
    @OneToOne
    private StoryEntity story;
    @OneToOne
    private OptionsEntity options;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "answeredQuestions", cascade = CascadeType.MERGE)
    private List<UserEntity> users;

    public QuestionEntity() {
    }

    public Long getId() {
        return id;
    }

    public QuestionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public QuestionEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QuestionEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAsk() {
        return ask;
    }

    public QuestionEntity setAsk(String ask) {
        this.ask = ask;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public QuestionEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public AnswerEntity getAnswer() {
        return answer;
    }

    public QuestionEntity setAnswer(AnswerEntity answer) {
        this.answer = answer;
        return this;
    }

    public ClueEntity getClue() {
        return clue;
    }

    public QuestionEntity setClue(ClueEntity clue) {
        this.clue = clue;
        return this;
    }

    public StoryEntity getStory() {
        return story;
    }

    public QuestionEntity setStory(StoryEntity story) {
        this.story = story;
        return this;
    }

    public OptionsEntity getOptions() {
        return options;
    }

    public QuestionEntity setOptions(OptionsEntity options) {
        this.options = options;
        return this;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public QuestionEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }
}

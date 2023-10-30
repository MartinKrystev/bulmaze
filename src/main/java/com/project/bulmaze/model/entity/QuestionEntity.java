package com.project.bulmaze.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class QuestionEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String ask;

    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    @OneToOne
    private AnswerEntity answer;

    @OneToOne
    private ClueEntity clue;

    @OneToOne
    private StoryEntity story;

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
}

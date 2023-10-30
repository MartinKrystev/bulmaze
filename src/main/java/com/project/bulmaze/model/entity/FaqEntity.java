package com.project.bulmaze.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "faqs")
public class FaqEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;

    public Long getId() {
        return id;
    }

    public FaqEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public FaqEntity setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public FaqEntity setAnswer(String answer) {
        this.answer = answer;
        return this;
    }
}

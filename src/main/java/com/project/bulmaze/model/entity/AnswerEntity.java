package com.project.bulmaze.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "answers")
public class AnswerEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 1)
    private String description;

    public AnswerEntity() {
    }

    public Long getId() {
        return id;
    }

    public AnswerEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AnswerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AnswerEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}

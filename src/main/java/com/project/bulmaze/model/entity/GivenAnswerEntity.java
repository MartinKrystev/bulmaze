package com.project.bulmaze.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "given_answers")
public class GivenAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 1)
    private String description;

    public Long getId() {
        return id;
    }

    public GivenAnswerEntity setId(Long id) {
        this.id = id;
        return this;
    }



    public String getDescription() {
        return description;
    }

    public GivenAnswerEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}

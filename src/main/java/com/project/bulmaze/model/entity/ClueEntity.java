package com.project.bulmaze.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clues")
public class ClueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    public ClueEntity() {
    }

    public Long getId() {
        return id;
    }

    public ClueEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClueEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClueEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}

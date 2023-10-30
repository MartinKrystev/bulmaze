package com.project.bulmaze.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stories")
public class StoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    public StoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public StoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StoryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}

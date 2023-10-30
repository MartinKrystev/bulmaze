package com.project.bulmaze.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "achievements")
public class AchievementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    public AchievementEntity() {
    }

    public Long getId() {
        return id;
    }

    public AchievementEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AchievementEntity setName(String name) {
        this.name = name;
        return this;
    }
}

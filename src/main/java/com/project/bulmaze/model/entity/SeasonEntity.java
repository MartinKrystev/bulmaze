package com.project.bulmaze.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "seasons")
public class SeasonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    public SeasonEntity() {
    }

    public Long getId() {
        return id;
    }

    public SeasonEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SeasonEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public SeasonEntity setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public SeasonEntity setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }
}

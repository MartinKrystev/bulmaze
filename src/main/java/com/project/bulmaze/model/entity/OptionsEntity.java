package com.project.bulmaze.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class OptionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String first;
    @Column(nullable = false)
    private String second;
    @Column(nullable = false)
    private String third;
    @Column(nullable = false)
    private String fourth;

    public Long getId() {
        return id;
    }

    public OptionsEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirst() {
        return first;
    }

    public OptionsEntity setFirst(String first) {
        this.first = first;
        return this;
    }

    public String getSecond() {
        return second;
    }

    public OptionsEntity setSecond(String second) {
        this.second = second;
        return this;
    }

    public String getThird() {
        return third;
    }

    public OptionsEntity setThird(String third) {
        this.third = third;
        return this;
    }

    public String getFourth() {
        return fourth;
    }

    public OptionsEntity setFourth(String fourth) {
        this.fourth = fourth;
        return this;
    }
}

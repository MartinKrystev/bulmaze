package com.project.bulmaze.model.dto;

import jakarta.persistence.Column;

public class OptionsDTO {
    private Long id;

    private String first;

    private String second;

    private String third;

    private String fourth;

    public Long getId() {
        return id;
    }

    public OptionsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirst() {
        return first;
    }

    public OptionsDTO setFirst(String first) {
        this.first = first;
        return this;
    }

    public String getSecond() {
        return second;
    }

    public OptionsDTO setSecond(String second) {
        this.second = second;
        return this;
    }

    public String getThird() {
        return third;
    }

    public OptionsDTO setThird(String third) {
        this.third = third;
        return this;
    }

    public String getFourth() {
        return fourth;
    }

    public OptionsDTO setFourth(String fourth) {
        this.fourth = fourth;
        return this;
    }
}

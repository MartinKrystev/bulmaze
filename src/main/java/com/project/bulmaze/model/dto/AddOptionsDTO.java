package com.project.bulmaze.model.dto;

import jakarta.validation.constraints.Size;

public class AddOptionsDTO {
    @Size(min = 2, max = 50, message = "First length must be between 2 and 50 characters!")
    private String first;
    @Size(min = 2, max = 50, message = "Second length must be between 2 and 50 characters!")
    private String second;
    @Size(min = 2, max = 50, message = "Third length must be between 2 and 50 characters!")
    private String third;
    @Size(min = 2, max = 50, message = "Fourth length must be between 2 and 50 characters!")
    private String fourth;

    public String getFirst() {
        return first;
    }

    public AddOptionsDTO setFirst(String first) {
        this.first = first;
        return this;
    }

    public String getSecond() {
        return second;
    }

    public AddOptionsDTO setSecond(String second) {
        this.second = second;
        return this;
    }

    public String getThird() {
        return third;
    }

    public AddOptionsDTO setThird(String third) {
        this.third = third;
        return this;
    }

    public String getFourth() {
        return fourth;
    }

    public AddOptionsDTO setFourth(String fourth) {
        this.fourth = fourth;
        return this;
    }
}

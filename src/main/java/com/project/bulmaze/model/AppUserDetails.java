package com.project.bulmaze.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AppUserDetails extends User {
    private String fullName;
    private String country;
    private int userProgress;


    public AppUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getFullName() {
        return fullName;
    }

    public AppUserDetails setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AppUserDetails setCountry(String country) {
        this.country = country;
        return this;
    }

    public int getUserProgress() {
        return userProgress;
    }

    public AppUserDetails setUserProgress(int userProgress) {
        this.userProgress = userProgress;
        return this;
    }
}

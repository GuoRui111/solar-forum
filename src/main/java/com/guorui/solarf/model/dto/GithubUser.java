package com.guorui.solarf.model.dto;

import lombok.Builder;

/**
 * @Author: GuoRUi
 * @Date: 2019/10/25 下午3:27
 * @Version 1.0
 */
@Builder
public class GithubUser {
    private String name;
    private Long id;
    private String bio;

    public GithubUser() {
    }

    public GithubUser(String name, Long id, String bio) {
        this.name = name;
        this.id = id;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }

    public static void main(String[] args) {
    }
}

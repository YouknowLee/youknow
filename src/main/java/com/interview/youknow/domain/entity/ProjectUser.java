package com.interview.youknow.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
public class ProjectUser {

    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="user_pw")
    private String userPw;

    public ProjectUser() {}

    public ProjectUser(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }
}

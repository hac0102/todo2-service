package com.hac.todo.config.auth.dto;

import com.hac.todo.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private long id;
    private String name;
    private String email;
    private String picture;
    private String joinType;


    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.joinType = user.getJoinType();
    }
}

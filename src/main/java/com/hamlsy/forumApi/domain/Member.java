package com.hamlsy.forumApi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String nickname;

    @NotNull
    private String userId;

    @NotNull
    private String password;

    //Member 부모 객체 삭제 시 고아 객체 방지
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Member(String name, String nickname, String userId, String password){
        this.name = name;
        this.nickname = nickname;
        this.userId = userId;
        this.password = password;
    }

}

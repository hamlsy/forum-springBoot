package com.hamlsy.springForum.domain;

import jakarta.persistence.*;
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

    private String name;
    private String nickname;
    private String userId;
    private String password;

    private MemberRole role;

    //Member 부모 객체 삭제 시 고아 객체 방지
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Member(String name, String nickname, String userId, String password, MemberRole role){
        this.name = name;
        this.nickname = nickname;
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

}

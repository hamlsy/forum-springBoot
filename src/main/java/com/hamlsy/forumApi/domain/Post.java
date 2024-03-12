package com.hamlsy.forumApi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private LocalDateTime postTime;

    private String subject;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public Post(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }


    //연관관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.getPosts().add(this);
    }

    //생성 메서드
    @Builder
    public static Post createPost(Member member, String subject, String content, LocalDateTime postTime){
        Post post = new Post();
        post.setMember(member);
        post.setSubject(subject);
        post.setContent(content);
        post.setPostTime(postTime);
        return post;
    }


    //비즈니스 로직

    //update 더티체킹

    public void update(String subject, String content){
        this.subject = subject;
        this.content = content;
    }

}

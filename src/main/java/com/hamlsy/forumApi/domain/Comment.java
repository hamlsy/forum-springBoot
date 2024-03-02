package com.hamlsy.forumApi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private LocalDateTime commentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //연관관계 메서드
    public void setPost(Post post){
        this.post = post;
        post.getComments().add(this);
    }

    public void setMember(Member member){
        this.member = member;
        member.getComments().add(this);
    }

    //생성 메서드
    public static Comment createComment(Post post, Member member, String content){
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setPost(post);
        comment.setContent(content);
        return comment;
    }
}

package com.hamlsy.forumApi.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private LocalDateTime commentTime;

    //아직 활용 안됨
    private LocalDateTime modifyTime;

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

    @Builder
    public Comment(String content, LocalDateTime commentTime, Post post, Member member) {
        this.content = content;
        this.commentTime = commentTime;
        this.post = post;
        this.member = member;
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

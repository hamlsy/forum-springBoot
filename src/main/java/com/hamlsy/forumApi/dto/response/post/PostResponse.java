package com.hamlsy.forumApi.dto.response.post;

import com.hamlsy.forumApi.domain.Comment;
import com.hamlsy.forumApi.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

    private String subject;
    private String content;
    private LocalDateTime postTime;
    private Long postId;
    private String nickname;

    //인증
    private String userId;
    private List<Comment> comments;

    @Builder
    public PostResponse(String subject, String content, LocalDateTime postTime, List<Comment> comments, Long postId, String nickname, String userId) {
        this.subject = subject;
        this.content = content;
        this.postTime = postTime;
        this.comments = comments;
        this.nickname = nickname;
        this.postId = postId;
        this.userId = userId;
    }

    public static PostResponse fromEntity(Post post){
        return PostResponse.builder()
                .subject(post.getSubject())
                .content(post.getContent())
                .postTime(post.getPostTime())
                .comments(post.getComments())
                .postId(post.getId())
                //Member 2번 호출 -> refactor 필요
                .nickname(post.getMember().getNickname())
                .userId(post.getMember().getUserId())
                .build();
    }
}

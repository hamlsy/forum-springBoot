package com.hamlsy.springForum.dto.response.post;

import com.hamlsy.springForum.domain.Post;
import com.hamlsy.springForum.dto.response.CommentWriteResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<CommentWriteResponse> comments;

    @Builder
    public PostResponse(String subject, String content, LocalDateTime postTime, List<CommentWriteResponse> comments, Long postId, String nickname, String userId) {
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
                //entity -> dto
                .comments(post.getComments().stream()
                        .map(c -> CommentWriteResponse.fromEntity(c))
                        .collect(Collectors.toList()))
                .postId(post.getId())
                //Member 2번 호출 -> refactor 필요
                .nickname(post.getMember().getNickname())
                .userId(post.getMember().getUserId())
                .build();
    }
}

package com.hamlsy.forumApi.dto.response;

import com.hamlsy.forumApi.domain.Comment;
import com.hamlsy.forumApi.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentWriteResponse {

    private Long commentId;
    private String content;
    private String nickname;
    private LocalDateTime commentTime;
    private String userId;

    @Builder
    public CommentWriteResponse(String content, String nickname, LocalDateTime commentTime, Long commentId, String userId) {
        this.content = content;
        this.nickname = nickname;
        this.commentTime = commentTime;
        this.userId = userId;
    }

    public static CommentWriteResponse fromEntity(Comment comment){
        return CommentWriteResponse.builder()
                .content(comment.getContent())
                .commentTime(LocalDateTime.now())
                .commentId(comment.getId())
                //member 2번 호출
                .nickname(comment.getMember().getNickname())
                .userId(comment.getMember().getUserId())
                .build();
    }
}

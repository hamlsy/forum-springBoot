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
    private String content;
    private String nickname;
    private LocalDateTime commentTime;
    @Builder
    public CommentWriteResponse(String content, String nickname, LocalDateTime commentTime) {
        this.content = content;
        this.nickname = nickname;
        this.commentTime = commentTime;
    }

    public static CommentWriteResponse fromEntity(Comment comment){
        return CommentWriteResponse.builder()
                .content(comment.getContent())
                .nickname(comment.getMember().getNickname())
                .commentTime(LocalDateTime.now())
                .build();
    }
}

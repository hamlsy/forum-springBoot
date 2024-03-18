package com.hamlsy.forumApi.dto.response.post;

import com.hamlsy.forumApi.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostListResponse {
    private Long id;
    private String subject;
    private LocalDateTime postTime;
    private String nickname;

    @Builder
    public PostListResponse(String subject, LocalDateTime postTime, String nickname, Long id) {
        this.subject = subject;
        this.postTime = postTime;
        this.nickname = nickname;
        this.id = id;
    }

    public static PostListResponse fromEntity(Post post){
        return PostListResponse.builder()
                .subject(post.getSubject())
                .postTime(post.getPostTime())
                .nickname(post.getMember().getNickname())
                .id(post.getId())
                .build();
    }
}

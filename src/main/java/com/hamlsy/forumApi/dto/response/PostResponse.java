package com.hamlsy.forumApi.dto.response;

import com.hamlsy.forumApi.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

    private String subject;
    private String content;
    private LocalDateTime postTime;

    @Builder
    public PostResponse(String subject, String content, LocalDateTime postTime) {
        this.subject = subject;
        this.content = content;
        this.postTime = postTime;
    }

    public static PostResponse fromEntity(Post post){
        return PostResponse.builder()
                .subject(post.getSubject())
                .content(post.getContent())
                .postTime(post.getPostTime())
                .build();
    }
}

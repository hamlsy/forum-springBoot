package com.hamlsy.forumApi.dto.response;

import com.hamlsy.forumApi.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
    private String subject;
    private String content;

    @Builder
    public PostResponse(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public static PostResponse fromEntity(Post post){
        return PostResponse.builder()
                .subject(post.getSubject())
                .content(post.getContent())
                .build();
    }
}

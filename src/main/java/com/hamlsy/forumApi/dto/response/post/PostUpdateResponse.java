package com.hamlsy.forumApi.dto.response.post;

import com.hamlsy.forumApi.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateResponse {
    private String subject;
    private String content;

    @Builder
    public PostUpdateResponse(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public static PostUpdateResponse fromEntity(Post post){
        return PostUpdateResponse.builder()
                .subject(post.getSubject())
                .content(post.getContent())
                .build();
    }
}

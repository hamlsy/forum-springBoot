package com.hamlsy.forumApi.dto.response;

import com.hamlsy.forumApi.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUploadResponse {

    private String subject;
    private String content;

    @Builder
    public PostUploadResponse(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public static PostUploadResponse fromEntity(Post post){
        return PostUploadResponse.builder()
                .content(post.getContent())
                .subject(post.getSubject())
                .build();
    }
}

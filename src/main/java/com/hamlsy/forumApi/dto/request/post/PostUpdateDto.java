package com.hamlsy.forumApi.dto.request.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateDto {
    private String subject;
    private String content;
    private Long postId;

    @Builder
    public PostUpdateDto(Long postId, String subject, String content) {
        this.postId = postId;
        this.subject = subject;
        this.content = content;
    }
}

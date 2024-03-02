package com.hamlsy.forumApi.dto.request.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private String subject;
    private String content;

    @Builder
    public PostDto(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}

package com.hamlsy.forumApi.dto.request.post;

import com.hamlsy.forumApi.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUploadDto {
    private String subject;
    private String content;

    @Builder
    public PostUploadDto(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public static Post toEntity(PostUploadDto dto){
        return Post.builder()
                .content(dto.getContent())
                .subject(dto.getSubject())
                .build();
    }
}

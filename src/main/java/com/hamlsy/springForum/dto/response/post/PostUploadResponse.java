package com.hamlsy.springForum.dto.response.post;

import com.hamlsy.springForum.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostUploadResponse {

    private String subject;
    private String content;
    private LocalDateTime postTime;

    @Builder
    public PostUploadResponse(String subject, String content, LocalDateTime postTime) {
        this.subject = subject;
        this.content = content;
        this.postTime = postTime;
    }

    public static PostUploadResponse fromEntity(Post post){
        return PostUploadResponse.builder()
                .content(post.getContent())
                .subject(post.getSubject())
                .postTime(post.getPostTime())
                .build();
    }
}

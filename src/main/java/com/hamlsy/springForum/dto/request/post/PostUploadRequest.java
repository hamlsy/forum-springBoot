package com.hamlsy.springForum.dto.request.post;

import com.hamlsy.springForum.domain.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostUploadRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String subject;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    private LocalDateTime postTime;

    @Builder
    public PostUploadRequest(String subject, String content, LocalDateTime postTime) {
        this.subject = subject;
        this.content = content;
        this.postTime = postTime;
    }

    public static Post toEntity(PostUploadRequest dto){
        return Post.builder()
                .content(dto.getContent())
                .subject(dto.getSubject())
                .postTime(dto.getPostTime())
                .build();

    }
}

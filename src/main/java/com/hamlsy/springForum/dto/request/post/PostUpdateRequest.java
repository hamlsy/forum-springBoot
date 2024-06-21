package com.hamlsy.springForum.dto.request.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String subject;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private Long postId;

    @Builder
    public PostUpdateRequest(Long postId, String subject, String content) {
        this.postId = postId;
        this.subject = subject;
        this.content = content;
    }
}

package com.hamlsy.springForum.dto.request.comment;

import com.hamlsy.springForum.domain.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentWriteRequest {
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    public CommentWriteRequest(String content) {
        this.content = content;
    }

    public static Comment toEntity(CommentWriteRequest request){
        return Comment.builder()
                .content(request.getContent())
                .build();
    }
}

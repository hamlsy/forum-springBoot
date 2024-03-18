package com.hamlsy.forumApi.dto.request.comment;

import com.hamlsy.forumApi.domain.Comment;
import com.hamlsy.forumApi.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentWriteRequest {
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

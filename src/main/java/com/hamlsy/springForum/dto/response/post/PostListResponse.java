package com.hamlsy.springForum.dto.response.post;

import com.hamlsy.springForum.domain.Post;
import com.hamlsy.springForum.repository.PostRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostListResponse {
    private Long id;
    private String subject;
    private LocalDateTime postTime;
    private String nickname;

    @Builder
    public PostListResponse(String subject, LocalDateTime postTime, String nickname, Long id) {
        this.subject = subject;
        this.postTime = postTime;
        this.nickname = nickname;
        this.id = id;
    }

    public static PostListResponse fromEntity(Post post){
        return PostListResponse.builder()
                .subject(post.getSubject())
                .postTime(post.getPostTime())
                .nickname(post.getMember().getNickname())
                .id(post.getId())
                .build();
    }
}

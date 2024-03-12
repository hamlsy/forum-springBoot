package com.hamlsy.forumApi.dto.response;

import com.hamlsy.forumApi.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {
    private String userId;
    private String password;
    private String name;
    private String nickname;

    @Builder
    public MemberResponse(String userId, String password, String name, String nickname){
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }

    public static MemberResponse fromEntity(Member member){
        return MemberResponse.builder()
                .name(member.getName())
                .nickname(member.getNickname())
                .userId(member.getUserId())
                .password(member.getPassword())
                .build();
    }
}

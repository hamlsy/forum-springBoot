package com.hamlsy.forumApi.dto.request.member;

import com.hamlsy.forumApi.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginRequest {
    private String userId;
    private String password;

    @Builder
    public MemberLoginRequest(String userId, String password){
        this.userId = userId;
        this.password = password;
    }

    public static Member toEntity(MemberLoginRequest memberLoginDto){
        return Member.builder()
                .userId(memberLoginDto.getUserId())
                .password(memberLoginDto.getPassword())
                .build();
    }

}

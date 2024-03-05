package com.hamlsy.forumApi.dto.request.member;

import com.hamlsy.forumApi.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDeleteDto {
    private String userId;
    private String password;

    @Builder
    public MemberDeleteDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public static Member toEntity(MemberDeleteDto dto){
        return Member.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .build();
    }
}

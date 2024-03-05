package com.hamlsy.forumApi.dto.request.member;

import com.hamlsy.forumApi.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private String userId;
    private String nickname;
    private String name;
    private String password;

    @Builder
    public MemberDto(String userId, String nickname, String name, String password) {
        this.userId = userId;
        this.nickname = nickname;
        this.name = name;
        this.password = password;
    }


    public static Member toEntity(MemberDto dto){
        return Member.builder()
                .userId(dto.getUserId())
                .nickname(dto.getNickname())
                .password(dto.getPassword())
                .name(dto.getName())
                .build();
    }

}

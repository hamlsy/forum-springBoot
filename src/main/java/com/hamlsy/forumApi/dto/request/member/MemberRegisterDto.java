package com.hamlsy.forumApi.dto.request.member;

import com.hamlsy.forumApi.domain.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterDto {
    //회원가입에 필요한 정보
    private String name;
    private String userId;
    private String password;
    private String nickname;

    @Builder
    public MemberRegisterDto(String name, String userId, String password, String nickname) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
    }

    //Dto -> Entity
    public static Member toEntity(MemberRegisterDto dto){
        Member member = Member.builder()
                .name(dto.getName())
                .nickname(dto.getNickname())
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .build();
        return member;
    }
}

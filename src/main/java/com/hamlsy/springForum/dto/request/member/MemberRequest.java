package com.hamlsy.springForum.dto.request.member;

import com.hamlsy.springForum.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequest {
    private String userId;
    private String nickname;
    private String name;
    private String password;



    public MemberRequest(String userId, String nickname, String name, String password) {
        this.userId = userId;
        this.nickname = nickname;
        this.name = name;
        this.password = password;
    }


    public static Member toEntity(MemberRequest dto){
        return Member.builder()
                .userId(dto.getUserId())
                .nickname(dto.getNickname())
                .password(dto.getPassword())
                .name(dto.getName())
                .build();
    }

}

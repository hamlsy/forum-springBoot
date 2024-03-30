package com.hamlsy.springForum.dto.request.member;

import com.hamlsy.springForum.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDeleteRequest {
    private String userId;
    private String password;


    public MemberDeleteRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public static Member toEntity(MemberDeleteRequest dto){
        return Member.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .build();
    }
}

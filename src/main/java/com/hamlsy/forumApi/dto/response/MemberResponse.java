package com.hamlsy.forumApi.dto.response;

import com.hamlsy.forumApi.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {
    private String userId;
    private String password;

    public MemberResponse(String userId, String password){
        this.userId = userId;
        this.password = password;
    }

    public static MemberResponse fromEntity(Member member){
        MemberResponse memberResponse = new MemberResponse(
                member.getUserId(), member.getPassword()
        );
        return memberResponse;
    }
}

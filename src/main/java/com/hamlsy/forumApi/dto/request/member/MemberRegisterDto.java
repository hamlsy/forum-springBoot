package com.hamlsy.forumApi.dto.request.member;

import com.hamlsy.forumApi.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterDto {
    //회원가입에 필요한 정보
    @NotBlank(message = "이름은 필수 입력입니다.")
    private String name;
    @NotBlank(message = "ID는 필수 입력입니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    private String password;
    @NotBlank(message = "닉네임은 필수 입력입니다.")
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

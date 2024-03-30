package com.hamlsy.springForum.dto.request.member;

import com.hamlsy.springForum.domain.Member;
import com.hamlsy.springForum.domain.MemberRole;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterRequest {
    //회원가입에 필요한 정보
    @NotBlank(message = "이름은 필수 입력입니다.")
    private String name;
    @NotBlank(message = "ID는 필수 입력입니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    private String password;
    @NotBlank(message = "닉네임은 필수 입력입니다.")
    private String nickname;

    public MemberRegisterRequest(String name, String userId, String password, String nickname) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
    }

    //Dto -> Entity
    public Member toEntity(PasswordEncoder passwordEncoder){
        return Member.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .nickname(nickname)
                .role(MemberRole.MEMBER)
                .build();
    }
}

package com.hamlsy.forumApi.api;

import com.hamlsy.forumApi.service.MemberService;
import com.hamlsy.forumApi.dto.request.member.MemberDeleteRequest;
import com.hamlsy.forumApi.dto.request.member.MemberLoginRequest;
import com.hamlsy.forumApi.dto.request.member.MemberRegisterRequest;
import com.hamlsy.forumApi.dto.response.MemberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberApi")
public class MemberApiController {
    private final MemberService memberService;

    //회원 전체 조회
    @GetMapping("/list")
    public List<MemberResponse> membersList(){
        List<MemberResponse> collect = memberService.findMembers();
        return collect;
    }

    //회원 단일 조회
    @GetMapping("/{id}")
    public MemberResponse findMember(@PathVariable("id") Long id){
        return memberService.findOne(id);
    }

    //로그인
    @PostMapping("/login")
    public MemberResponse loginMember(@RequestBody MemberLoginRequest memberLoginDto){
        MemberResponse memberResponse = memberService.login(memberLoginDto);
        return memberResponse;
    }

    //회원가입
    @PostMapping("/register")
    public MemberResponse registerMember(@RequestBody @Valid MemberRegisterRequest memberRegisterRequest){
        MemberResponse memberResponse = memberService.join(memberRegisterRequest);
        return memberResponse;
    }

    //회원 탈퇴
    @PostMapping("/delete")
    public MemberResponse deleteMember(@RequestBody MemberDeleteRequest dto){
        MemberResponse response = memberService.deleteMember(dto);
        return response;
    }

}

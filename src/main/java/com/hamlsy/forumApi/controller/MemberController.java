package com.hamlsy.forumApi.controller;

import com.hamlsy.forumApi.dto.response.MemberResponse;
import com.hamlsy.forumApi.service.MemberService;
import com.hamlsy.forumApi.dto.request.member.MemberLoginDto;
import com.hamlsy.forumApi.dto.request.member.MemberRegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    //로그인
    @PostMapping("/login")
    public String login(MemberLoginDto dto){
        MemberResponse response = memberService.login(dto);
        return "redirect:/post/list";
    }


    //회원가입
    @GetMapping("/register")
    public String register(){
        return "register_member_form";
    }

    @PostMapping("/register")
    public String register(@Valid MemberRegisterDto dto){
        memberService.join(dto);
        return "redirect:/";
    }


}

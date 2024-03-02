package com.hamlsy.forumApi.controller;

import com.hamlsy.forumApi.Service.MemberService;
import com.hamlsy.forumApi.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
//    public Member register(Member member){
//
//        return member;
//    }
}

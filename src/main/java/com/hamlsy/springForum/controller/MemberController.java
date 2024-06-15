package com.hamlsy.springForum.controller;

import com.hamlsy.springForum.dto.response.MemberResponse;
import com.hamlsy.springForum.service.MemberService;
import com.hamlsy.springForum.dto.request.member.MemberLoginRequest;
import com.hamlsy.springForum.dto.request.member.MemberRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    //로그인
    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@RequestBody @Valid MemberLoginRequest dto){
        try {
            // 로그인 성공 시 처리
            MemberResponse member = memberService.login(dto);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (AuthenticationException e) {
            // 로그인 실패 시 처리
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    //회원가입
    @GetMapping("/register")
    public String register(){
        return "register_member_form";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid MemberRegisterRequest dto){
        try{
            memberService.join(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (IllegalStateException e){
            //회원가입 실패
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

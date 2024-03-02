package com.hamlsy.forumApi.api;

import com.hamlsy.forumApi.Service.MemberService;
import com.hamlsy.forumApi.dto.request.member.MemberLoginDto;
import com.hamlsy.forumApi.dto.request.member.MemberRegisterDto;
import com.hamlsy.forumApi.dto.response.MemberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {
    private final MemberService memberService;

//    @GetMapping("/list")
//    public List<MemberDto> membersList(){
//        List<Member> memberList = memberService.findMembers();
//        //entity -> DTO
//        List<MemberDto> collect = memberList.stream()
//                .map(m -> new MemberDto(m))
//                .collect(Collectors.toList());
//        return collect;
//    }


    //로그인
    @PostMapping("/login")
    public MemberResponse loginMember(@RequestBody MemberLoginDto memberLoginDto){
        MemberResponse memberResponse = memberService.login(memberLoginDto);
        return memberResponse;
    }

    //회원가입
    @PostMapping("/register")
    public MemberResponse registerMember(@RequestBody @Valid MemberRegisterDto memberRegisterDto){
        MemberResponse memberResponse = memberService.join(memberRegisterDto);
        return memberResponse;
    }

    //회원 탈퇴

//    @PostMapping("/remove")
//    public ResponseMember remove(@RequestBody Member member){
//        Member findMember = memberService.findByUserId(member.getUserId());
//        Long memberId = memberService.removeMember(findMember);
//        return new ResponseMember(memberId);
//    }

}

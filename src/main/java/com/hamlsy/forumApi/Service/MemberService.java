package com.hamlsy.forumApi.Service;

import com.hamlsy.forumApi.domain.Member;
import com.hamlsy.forumApi.dto.request.member.MemberLoginDto;
import com.hamlsy.forumApi.dto.request.member.MemberRegisterDto;
import com.hamlsy.forumApi.dto.response.MemberResponse;
import com.hamlsy.forumApi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public MemberResponse join(MemberRegisterDto memberRegisterDto){
        Member member = memberRegisterDto.toEntity(memberRegisterDto);
        validateDuplicateUserId(member);
        memberRepository.save(member);
        return MemberResponse.fromEntity(member);
    }
    //중복 검증
    private void validateDuplicateUserId(Member member){
        List<Member> members = memberRepository.findByUserId(member.getUserId());
        if(!members.isEmpty()){
            throw new IllegalStateException("중복된 아이디가 존재합니다");
        }
    }

    //로그인
    @Transactional
    public MemberResponse login(MemberLoginDto memberLoginDto){
        Member member = memberLoginDto.toEntity(memberLoginDto);
        return MemberResponse.fromEntity(member);
    }

    //단일 회원 조회
    public Member findOne(Long id){
        return memberRepository.findById(id);
    }

    public Member findByUserId(String userId){
        return memberRepository.findOneByUserId(userId);
    }


    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 탈퇴
    @Transactional
    public Long removeMember(Member member){
        memberRepository.removeMember(member);
        return member.getId();
    }
}

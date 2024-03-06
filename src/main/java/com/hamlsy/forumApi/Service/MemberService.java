package com.hamlsy.forumApi.Service;

import com.hamlsy.forumApi.domain.Member;
import com.hamlsy.forumApi.dto.request.member.MemberDeleteDto;
import com.hamlsy.forumApi.dto.request.member.MemberLoginDto;
import com.hamlsy.forumApi.dto.request.member.MemberRegisterDto;
import com.hamlsy.forumApi.dto.response.MemberResponse;
import com.hamlsy.forumApi.repository.MemberRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    //단일 회원 id로 조회
    public MemberResponse findOne(Long id){
        Member member = memberRepository.findById(id);
        MemberResponse response = MemberResponse.fromEntity(member);
        return response;
    }

    public Member findByUserId(String userId){
        return memberRepository.findOneByUserId(userId);
    }


    //전체 회원 조회
    public List<MemberResponse> findMembers(){
        //parameter 값 설정, dto
        //entity -> MemberResponse
        List<MemberResponse> memberList = memberRepository.findAll().stream()
                .map(m -> MemberResponse.fromEntity(m))
                .collect(Collectors.toList());
        return memberList;
    }

    //회원 탈퇴
    @Transactional
    public MemberResponse deleteMember(MemberDeleteDto dto){
        //계정 검증
        validateUserId(dto);
        validatePassword(dto);
        Member member = memberRepository.findOneByUserId(dto.getUserId());
        memberRepository.removeMember(member);
        MemberResponse response = MemberResponse.fromEntity(member);
        return response;
    }

    //id 존재 검증
    private void validateUserId(MemberDeleteDto dto){
        List<Member> members = memberRepository.findByUserId(dto.getUserId());
        if(members.isEmpty()){
            throw new NoResultException("존재하지 않는 Id입니다.");
        }
    }

    //password 일치 검증
    private void validatePassword(MemberDeleteDto dto){
        Member member = memberRepository.findOneByUserId(dto.getUserId());
        if(member.getPassword() != dto.getPassword()){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }

}

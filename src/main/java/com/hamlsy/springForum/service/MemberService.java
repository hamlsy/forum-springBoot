package com.hamlsy.springForum.service;

import com.hamlsy.springForum.domain.Member;
import com.hamlsy.springForum.dto.request.member.MemberDeleteRequest;
import com.hamlsy.springForum.dto.request.member.MemberLoginRequest;
import com.hamlsy.springForum.dto.request.member.MemberRegisterRequest;
import com.hamlsy.springForum.dto.response.MemberResponse;
import com.hamlsy.springForum.repository.MemberRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    //회원가입
    @Transactional
    public MemberResponse join(MemberRegisterRequest memberRegisterRequest){
        Member member = memberRegisterRequest.toEntity(passwordEncoder);
        validateDuplicateUserId(member);
        memberRepository.save(member);
        return MemberResponse.fromEntity(member);
    }
    //중복 검증
    private void validateDuplicateUserId(Member member){
        List<Member> members = memberRepository.findByAllUserId(member.getUserId());
        if(!members.isEmpty()){
            throw new IllegalStateException("중복된 아이디가 존재합니다");
        }
    }

    //로그인
    @Transactional
    public MemberResponse login(MemberLoginRequest memberLoginDto){
        //검증
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            memberLoginDto.getUserId(),
                            memberLoginDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Member member = memberRepository.findByUserId(memberLoginDto.getUserId());
            return MemberResponse.fromEntity(member);
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Invalid username or password");
        }

    }

    //단일 회원 id로 조회
    public MemberResponse findOne(Long id){
        Member member = memberRepository.findById(id);
        MemberResponse response = MemberResponse.fromEntity(member);
        return response;
    }

    public Member findByUserId(String userId){
        return memberRepository.findByUserId(userId);
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
    public MemberResponse deleteMember(MemberDeleteRequest dto){
        //계정 검증
        validateUserId(dto);
        validatePassword(dto);
        Optional<Member> _member = Optional.ofNullable(memberRepository.findByUserId(dto.getUserId()));
        Member member = _member.get();
        memberRepository.removeMember(member);
        MemberResponse response = MemberResponse.fromEntity(member);
        return response;
    }

    //id 존재 검증
    private void validateUserId(MemberDeleteRequest dto){
        List<Member> members = memberRepository.findByAllUserId(dto.getUserId());
        if(members.isEmpty()){
            throw new NoResultException("존재하지 않는 Id입니다.");
        }
    }

    //password 일치 검증
    private void validatePassword(MemberDeleteRequest dto){
        Optional<Member> _member = Optional.ofNullable(memberRepository.findByUserId(dto.getUserId()));
        Member member = _member.get();

        //-> equals
        if(member.getPassword() != dto.getPassword()){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }

}

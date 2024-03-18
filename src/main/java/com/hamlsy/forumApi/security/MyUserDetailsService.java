package com.hamlsy.forumApi.security;

import com.hamlsy.forumApi.domain.Member;
import com.hamlsy.forumApi.domain.MemberRole;
import com.hamlsy.forumApi.repository.MemberRepository;
import com.hamlsy.forumApi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(userId);
        return User.builder()
                .username(member.getUserId())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }


}

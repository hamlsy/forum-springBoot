package com.hamlsy.forumApi.security;

import com.hamlsy.forumApi.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyMemberDetails implements UserDetails {
    private final Member member;

    public MyMemberDetails(Member member) {
        this.member = member;
    }

    public String getUserId(){
        return member.getUserId();
    }
    public String getNickname(){
        return member.getNickname();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

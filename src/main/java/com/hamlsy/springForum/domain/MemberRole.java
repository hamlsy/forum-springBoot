package com.hamlsy.springForum.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    ADMIN("ROLE_ADMIN", "admin"),
    MEMBER("ROLE_MEMBER", "member");
    private final String key;
    private final String value;

}

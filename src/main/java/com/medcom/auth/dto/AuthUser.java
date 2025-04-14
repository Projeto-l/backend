package com.medcom.auth.dto;

import com.medcom.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {



    public AuthUser(User user) {
        super(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    private static Collection<GrantedAuthority> getAuthorities(User user) {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));

    }
}

package com.medcom.auth.service;



import com.medcom.auth.dto.AuthUser;
import com.medcom.auth.exception.UserEmailNotFoundException;
import com.medcom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(encoder.encode("123456"));
    }

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("Request authenticate for user {}", username);
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserEmailNotFoundException(username));
        var authUser = new AuthUser(user);
        return new UserAuthenticated(authUser);
    }

}

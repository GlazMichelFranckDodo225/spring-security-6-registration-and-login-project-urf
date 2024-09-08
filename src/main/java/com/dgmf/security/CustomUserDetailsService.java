package com.dgmf.security;

import com.dgmf.entity.User;
import com.dgmf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Load User from DB
        User user = userRepository.findByEmail(usernameOrEmail); // Can Be email or username
        if (user != null) {
            // Convert User from DB into Spring Security User Object and Return It
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map(
                            // Convert App List of Roles into Spring Security
                            // List of Authorities
                            role -> new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList())
            );
        } else {
            throw new UsernameNotFoundException("Invalid Username or Email");
        }
    }
}

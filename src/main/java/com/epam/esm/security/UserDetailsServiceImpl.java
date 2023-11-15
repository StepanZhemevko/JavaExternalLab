package com.epam.esm.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("house keyboard train".equals(username)) {
           return User.builder()
                    .username("house keyboard train")
                    .password("12345678")
                    .roles("ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

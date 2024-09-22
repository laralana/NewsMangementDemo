package com.demo.appswave.NewsMangement.service;

import com.demo.appswave.NewsMangement.entities.User;
import com.demo.appswave.NewsMangement.entities.UserDetailsImpl;
import com.demo.appswave.NewsMangement.enumeration.ERole;
import com.demo.appswave.NewsMangement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserDetailsImpl.build(user);
    }
    private Collection<? extends GrantedAuthority> getAuthorities(ERole ERole) {
        return List.of(new SimpleGrantedAuthority(ERole.name()));
    }
}

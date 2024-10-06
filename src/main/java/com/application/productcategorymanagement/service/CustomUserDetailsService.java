package com.application.productcategorymanagement.service;

import com.application.productcategorymanagement.entity.User;
import com.application.productcategorymanagement.model.CustomUserDetails;
import com.application.productcategorymanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        User user = optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username));

        return new CustomUserDetails(user);
    }
}

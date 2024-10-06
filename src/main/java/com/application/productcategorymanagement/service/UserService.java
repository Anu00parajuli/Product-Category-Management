package com.application.productcategorymanagement.service;

import com.application.productcategorymanagement.entity.Roles;
import com.application.productcategorymanagement.entity.User;
import com.application.productcategorymanagement.repository.RoleRepository;
import com.application.productcategorymanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Short registerAdmin(String username, String password) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            Roles adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error: Role 'ROLE_ADMIN' not found"));

            Set<Roles> roles = new HashSet<>();
            roles.add(adminRole);
            user.setRoles(roles);

            userRepository.save(user);
            return 1;
        } catch (Exception e){
            log.error(e.getMessage());
            return 0;
        }

    }

    public Short registerUser(String username, String password) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            Roles userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role 'ROLE_USER' not found"));
            Set<Roles> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);

            userRepository.save(user);
            return 1;
        } catch (Exception e){
            log.error( e.getMessage());
            return 0;
        }

    }
}

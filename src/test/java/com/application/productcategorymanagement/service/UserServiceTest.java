package com.application.productcategorymanagement.service;

import com.application.productcategorymanagement.entity.Roles;
import com.application.productcategorymanagement.entity.User;
import com.application.productcategorymanagement.repository.RoleRepository;
import com.application.productcategorymanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

 class UserServiceTest {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);// Initialize the mock UserRepository
        roleRepository = Mockito.mock(RoleRepository.class);
        userService = new UserService(userRepository, roleRepository);  // Pass the mocks to the service
    }

    @Test
     void testRegisterAdmin() {
        // Arrange
        String username = "admin";
        String rawPassword = "password";

        Set<Roles> roles = new HashSet<>();
        Roles roleAdmin = new Roles();
        roleAdmin.setName("ROLE_ADMIN");
        roles.add(roleAdmin);

        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin123");
        user.setRoles(roles);

        // Stubbing
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(roleAdmin));  // Simulate role retrieval
        when(userRepository.save(any(User.class))).thenReturn(user);  // Simulate successful save


        // Act
        Short actual = userService.registerAdmin(username, rawPassword);

        // Assert
        assertEquals((short) 1, actual);  // Assuming your registerAdmin method returns 1 when successful
    }

}


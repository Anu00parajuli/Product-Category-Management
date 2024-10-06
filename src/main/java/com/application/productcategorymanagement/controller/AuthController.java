package com.application.productcategorymanagement.controller;

import com.application.productcategorymanagement.model.AuthRequest;
import com.application.productcategorymanagement.service.CustomUserDetailsService;
import com.application.productcategorymanagement.service.UserService;
import com.application.productcategorymanagement.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String createToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    @PostMapping("/register/admin")
    public void registerAdmin(@RequestBody AuthRequest authRequest) {
        userService.registerAdmin(authRequest.getUsername(), authRequest.getPassword());
    }

    @PostMapping("/register/user")
    public void registerUser(@RequestBody AuthRequest authRequest) {
        userService.registerUser(authRequest.getUsername(), authRequest.getPassword());
    }
}

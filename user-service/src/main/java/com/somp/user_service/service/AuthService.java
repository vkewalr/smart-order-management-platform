package com.somp.user_service.service;

import com.somp.user_service.entity.User;
import com.somp.user_service.repository.UserRepository;
import com.somp.user_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;

    public String login(String email, String password){
        User user = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid Credentials"));

        if(!encoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        return jwtUtil.generateToken(email);
    }
}

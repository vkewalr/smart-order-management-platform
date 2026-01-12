package com.somp.user_service.controller;

import com.somp.user_service.dto.LoginRequest;
import com.somp.user_service.dto.LoginResponse;
import com.somp.user_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        String token = authService.login(request.getEmail(), request.getPassword());
        return new LoginResponse(token);
    }
}

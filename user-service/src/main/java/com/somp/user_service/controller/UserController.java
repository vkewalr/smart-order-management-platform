package com.somp.user_service.controller;

import com.somp.user_service.dto.UserRequest;
import com.somp.user_service.dto.UserResponse;
import com.somp.user_service.entity.User;
import com.somp.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public UserResponse create(@RequestBody @Valid UserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User saved = service.create(user);
        return new UserResponse(saved.getId(),saved.getName(),saved.getEmail());
    }

    @GetMapping
    public List<UserResponse> getAll(){
        return service.getAll().stream().map(user ->
            new UserResponse(user.getId(),user.getName(),user.getEmail())
        ).collect(Collectors.toList());
    }

    @GetMapping("/secure")
    public String secureEndPoint(Authentication auth){
        return "Hello" + auth.getName();
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String userProfile(Authentication auth){
        return "User profile for "+ auth.getName();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(Authentication auth){
        return "Admin Access only";
    }
}

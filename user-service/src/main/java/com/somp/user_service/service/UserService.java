package com.somp.user_service.service;

import com.somp.user_service.entity.User;
import com.somp.user_service.exception.DuplicateEmailException;
import com.somp.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public User create(User user){
        if(repository.findByEmail(user.getEmail()).isPresent()){
            throw new DuplicateEmailException("Email Id Already present in records");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        return repository.save(user);
    }

    public List<User> getAll(){
        return repository.findAll();
    }
}

package com.airline.reservation.service.impl;

import com.airline.reservation.entity.User;
import com.airline.reservation.form.UserForm;
import com.airline.reservation.repository.UserRepository;
import com.airline.reservation.security.config.SecurityConfig;
import com.airline.reservation.security.util.TokenGenerator;
import com.airline.reservation.service.UserService;
import com.airline.reservation.view.UserView;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
     
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserView add(UserForm form) {
        System.out.println("Welcome");
        return new UserView(userRepository.save(new User(
                form.getFullName(),
                form.getEmail(),
                form.getPhone(),
                passwordEncoder.encode(form.getPassword()),
                form.getRole(),
                form.getStatus(),
                form.getAddress(),
                form.getPassportNumber(),
                form.getCity(),
              
                form.getCountry(),
                form.getDob()
                )));
                
    }   
    
}

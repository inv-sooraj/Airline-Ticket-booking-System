package com.airline.reservation.service.impl;

import com.airline.reservation.entity.User;
import com.airline.reservation.exception.BadRequestException;
import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.LoginForm;
import com.airline.reservation.form.UserForm;
import com.airline.reservation.repository.UserRepository;
import com.airline.reservation.security.config.SecurityConfig;
import com.airline.reservation.security.util.InvalidTokenException;
import com.airline.reservation.security.util.SecurityUtil;
import com.airline.reservation.security.util.TokenExpiredException;
import com.airline.reservation.security.util.TokenGenerator;
import com.airline.reservation.security.util.TokenGenerator.Status;
import com.airline.reservation.security.util.TokenGenerator.Token;
import com.airline.reservation.service.UserService;
import com.airline.reservation.view.LoginView;
import com.airline.reservation.view.UserView;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.server.ResponseStatusException;

import static com.airline.reservation.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;
import java.util.Collection;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenGenerator tokenGenerator;
    
    public  Boolean emailCheck(String email){
        return userRepository.existsByEmail(email);
        
    }
    @Override
    public LoginView login(LoginForm form, Errors errors) throws BadRequestException {
        if (errors.hasErrors()) {
            throw badRequestException();
        }
        User user = userRepository.findByEmail(form.getEmail()).orElseThrow(UserServiceImpl::badRequestException);
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(), securityConfig.getRefreshTokenExpiry());
        return new LoginView(user, accessToken, refreshToken);
    }
    @Override
    public LoginView refresh(String refreshToken) throws BadRequestException {
        Status status;
        try {
            status = tokenGenerator.verify(PURPOSE_REFRESH_TOKEN, refreshToken);
        } catch (InvalidTokenException e) {
            throw new BadRequestException("Invalid token", e);
        } catch (TokenExpiredException e) {
            throw new BadRequestException("Token expired", e);
        }
        int userId;
        try {
            userId = Integer.parseInt(status.data.substring(0, 10));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid token", e);
        }

        String password = status.data.substring(10);

        User user = userRepository.findByUserIdAndPassword(userId, password).orElseThrow(UserServiceImpl::badRequestException);

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        return new LoginView(
                user,
                new LoginView.TokenView(accessToken.value, accessToken.expiry),
                new LoginView.TokenView(refreshToken, status.expiry)
        );
    }
    @Override
    public UserView add(UserForm form) {
        
        Optional<User> usernameEntry = userRepository.findByEmail(form.getEmail());
        if(usernameEntry.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }
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
  
    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }
    @Override
    public Collection<User> list() {
        
            return userRepository.findAll();

    }

    @Override
    public Collection<User> Search(String userName) {
        
        return userRepository.findByFullNameContaining(userName);

    }

    

    @Override
    public Collection<User> getCompany() {
        
         return userRepository.findAllByRole(2);
    }

   @Override
    @Transactional
    public UserView update(UserForm form) throws NotFoundException {
        return userRepository.findByUserId(SecurityUtil.getCurrentUserId())
                .map((contact) -> {
                    return new UserView(userRepository.save(contact.update(form)));
                }).orElseThrow(NotFoundException::new);
    }
   
    }
    
    

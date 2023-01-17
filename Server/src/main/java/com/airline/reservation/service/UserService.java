package com.airline.reservation.service;

import org.springframework.validation.Errors;

import com.airline.reservation.exception.BadRequestException;
import com.airline.reservation.form.LoginForm;
import com.airline.reservation.form.UserForm;
import com.airline.reservation.view.LoginView;
import com.airline.reservation.view.UserView;

public interface UserService {
    UserView add(UserForm form);
    LoginView login(LoginForm form, Errors errors) throws BadRequestException;
    LoginView refresh(String refreshToken) throws BadRequestException;
    Boolean emailCheck(String email);
}

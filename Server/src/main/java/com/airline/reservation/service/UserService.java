package com.airline.reservation.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import com.airline.reservation.exception.BadRequestException;
// import com.airline.reservation.exception.HandledException;
import com.airline.reservation.form.ChangePwdForm;
import com.airline.reservation.form.LoginForm;
import com.airline.reservation.form.UserForm;
import com.airline.reservation.json.ResBody;
import com.airline.reservation.security.util.UserAlreadyExistAuthenticationException;
import com.airline.reservation.view.LoginView;
import com.airline.reservation.view.UserView;

public interface UserService {
    ResponseEntity<ResBody> add(UserForm form);
    LoginView login(LoginForm form, Errors errors) throws BadRequestException;
    LoginView refresh(String refreshToken) throws BadRequestException;
    Boolean emailCheck(String email) throws BadRequestException;
    ResponseEntity<ResBody> changePwd(ChangePwdForm form);
    UserView currentUser();
}

package com.airline.reservation.service;

import com.airline.reservation.entity.User;
import com.airline.reservation.exception.BadRequestException;
import com.airline.reservation.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import com.airline.reservation.form.ChangePwdForm;
import com.airline.reservation.form.LoginForm;
import com.airline.reservation.form.UserForm;
import com.airline.reservation.form.UserUpdateForm;
import com.airline.reservation.json.ResBody;
import com.airline.reservation.view.LoginView;
import com.airline.reservation.view.UserView;
import java.util.ArrayList;
import java.util.Collection;

public interface UserService {

    ResponseEntity<ResBody> add(UserForm form);

    ResponseEntity<ResBody> login(LoginForm form);

    LoginView refresh(String refreshToken) throws BadRequestException;

    Boolean emailCheck(String email);

    public Collection<User> list();

    public Collection<User> getCompany();

    public Collection<User> Search(String userName);

    UserView update(UserUpdateForm form) throws NotFoundException;

    ResponseEntity<ResBody> changePwd(ChangePwdForm form);

    UserView currentUser();

    UserView get(Integer userId);

    UserView updateById(Integer userId, UserForm form);

    ResponseEntity<ResBody> changeStatus(Integer userId);

    public void deleteAllBYIds(ArrayList<Integer> ids);

    Integer getCurrent();

    public void sendEmail(String email, String password);
}

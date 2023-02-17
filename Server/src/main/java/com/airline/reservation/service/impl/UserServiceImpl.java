package com.airline.reservation.service.impl;

import com.airline.reservation.entity.User;
import com.airline.reservation.exception.ApplicationError;
import com.airline.reservation.exception.BadRequestException;
import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.LoginForm;
import com.airline.reservation.form.UserForm;
import com.airline.reservation.json.ResBody;
import com.airline.reservation.form.ChangePwdForm;
import com.airline.reservation.form.UserUpdateForm;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import static com.airline.reservation.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenGenerator tokenGenerator;

    //email existing check
    public Boolean emailCheck(String email) {

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
    public ResponseEntity<ResBody> add(@Valid UserForm form) {
        ResBody body = new ResBody();
        Optional<User> usernameEntry = userRepository.findByEmail(form.getEmail());
        if (!usernameEntry.isEmpty()) {
            body.getErrors().add(new ApplicationError("200", "User already exist"));
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        UserView uview = new UserView(userRepository.save(new User(
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
                form.getDob())));
        body.getValues().put("user", uview);
        return new ResponseEntity<ResBody>(body, HttpStatus.OK);
    }

    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }

    @Override
    public Collection<User> list() {
        var flag = 1;
        return userRepository.findAllByStatus(flag);
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
    public UserView update(@Valid UserUpdateForm form) throws NotFoundException {
        return userRepository.findByUserId(SecurityUtil.getCurrentUserId())
                .map((User) -> {
                    return new UserView(userRepository.save(User.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    public UserView currentUser() {
        // System.out.println(SecurityUtil.getCurrentUserId());
        return new UserView(
                userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public ResponseEntity<ResBody> changePwd(ChangePwdForm form) {
        ResBody body = new ResBody();
        System.out.println("change PWd");
        Integer userid = SecurityUtil.getCurrentUserId();
        System.out.println(SecurityUtil.getCurrentUserId());
        User user = userRepository.findByUserId(userid).orElseThrow(UserServiceImpl::badRequestException);
        System.out.println(user.getEmail());
        if (form.getCurrentPwd().matches(form.getNewPwd())) {
            body.getErrors().add(new ApplicationError("104", "Current and New Passwords are Same "));
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        } else if (!passwordEncoder.matches(form.getCurrentPwd(), user.getPassword())) {
            body.getErrors().add(new ApplicationError("105", "Current is Wrong "));
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        } else {
            System.out.println("new password is " + passwordEncoder.encode(form.getNewPwd()));
            user.setPassword(passwordEncoder.encode(form.getNewPwd()));
            userRepository.save(user);
            body.getValues().put("106", "Password changed Successfully");
            return new ResponseEntity<>(body, HttpStatus.OK);
        }

    }

    // user Details
    @Override
    public UserView get(Integer UserId) throws NotFoundException {
        return userRepository.findByUserId(UserId).map((User) -> {
            return new UserView(User);
        }).orElseThrow(NotFoundException::new);
    }

    public UserView updateById(Integer userId, UserForm form) {
        // TODO Auto-generated method stub
        User user = userRepository.findByUserId(userId).orElseThrow(UserServiceImpl::badRequestException);
        user.setFullName(form.getFullName());
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());
        user.setCity(form.getCity());
        user.setCountry(form.getCountry());
        user.setDob(form.getDob());
        user.setPassportNumber(form.getPassportNumber());
        user.setPhone(form.getPhone());
        return new UserView(userRepository.save(user));
    }

    public ResponseEntity<ResBody> changeStatus(Integer userId) {

        ResBody body = new ResBody();
        User user = userRepository.findByUserId(userId).orElseThrow(UserServiceImpl::badRequestException);
        user.setStatus(0);
        userRepository.save(user);
        body.getValues().put("108", "Deleted  user Successfully");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public void deleteAllBYIds(ArrayList<Integer> ids) {
        userRepository.softDeleteAllIds(ids);
    }
}

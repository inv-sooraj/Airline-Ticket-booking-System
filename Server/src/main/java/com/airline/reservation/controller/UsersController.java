package com.airline.reservation.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.airline.reservation.entity.User;
import com.airline.reservation.exception.HandledException;
import com.airline.reservation.form.ChangePwdForm;
import com.airline.reservation.form.UserForm;
import com.airline.reservation.json.Json;
import com.airline.reservation.security.util.SecurityUtil;
import com.airline.reservation.service.UserService;
import com.airline.reservation.view.UserView;
import javax.validation.Valid;
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping("/{email}")
    @ResponseBody
    public Boolean emailCheck(@PathVariable("email") String email){
        return userService.emailCheck(email);
    }
    
    @PostMapping("/signup")
    public UserView add(@Valid @RequestBody UserForm form) throws HandledException {
        return userService.add(form);
    }
    //change password - returns boolean value according to status
    @GetMapping("/changePwd")
    public boolean changePwd(@Valid @RequestBody ChangePwdForm pwdForm){
        return userService.changePwd();
    }
}

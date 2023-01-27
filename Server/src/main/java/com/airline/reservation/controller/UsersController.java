package com.airline.reservation.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import com.airline.reservation.entity.User;
import com.airline.reservation.form.UserForm;
import com.airline.reservation.json.Json;
import com.airline.reservation.service.UserService;
import com.airline.reservation.view.UserView;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserView add(@Valid @RequestBody UserForm form) {
        return userService.add(form);
    }
    @PostMapping("/{email}")
    public Boolean emailCheck(@PathVariable String email){
        return userService.emailCheck(email);
    }
    @GetMapping
    public Collection<User> list(Principal p) {
        return userService.list();
    }
    @GetMapping("/search/{userName}")
        public Collection<User> get(@PathVariable String userName){
        return userService.Search(userName);
    }
    @GetMapping("/GetCompany")
    public Collection<User> getByRole(Principal p) {
        return userService.getCompany();
    }
    @PutMapping
    public UserView update(
            @Valid @RequestBody UserForm form
    ) {
        return userService.update(form);
    }
}

package com.airline.reservation.service;

import com.airline.reservation.form.UserForm;
import com.airline.reservation.view.UserView;

public interface UserService {
    UserView add(UserForm form);
}

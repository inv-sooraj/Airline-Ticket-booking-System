/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airline.reservation.form;

import com.airline.reservation.form.validation.Password;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author lakshmimohan
 */
public class ChangePasswordForm {
    @Password
    private String newPassword;
    
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
}

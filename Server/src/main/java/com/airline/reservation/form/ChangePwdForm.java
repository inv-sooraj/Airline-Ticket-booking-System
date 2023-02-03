package com.airline.reservation.form;
import com.airline.reservation.form.validation.Password;
public class ChangePwdForm {
    @Password
    private String CurrentPwd;
    @Password
    private String NewPwd;
    public String getCurrentPwd() {
        return CurrentPwd;
    }
    public void setCurrentPwd(String currentPwd) {
        CurrentPwd = currentPwd;
    }
    public String getNewPwd() {
        return NewPwd;
    }
    public void setNewPwd(String newPwd) {
        NewPwd = newPwd;
    }
}

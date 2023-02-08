import { Component, OnInit } from '@angular/core';
import { AbstractControlOptions, Form, FormBuilder, FormGroup } from '@angular/forms';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';
import { ConfirmPasswordValidator } from 'src/app/confirm-password.validator';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
changePassowrdForm!:FormGroup
  constructor(private formbuilder:FormBuilder,private apiService:ApiService,private alertService:AlertService) { }

  ngOnInit(): void {

  }
  changePasswordForm=this.formbuilder.group({
currentPwd:[''],
newPwd:[''],
cPwd:['']
  },
  {
    validator: ConfirmPasswordValidator("currentPwd", "cPwd")
  }as AbstractControlOptions );
  register(){
    if(this.changePasswordForm.valid){
      let param ={
        "currentPwd":this.changePasswordForm.value.currentPwd,
        "newPwd":this.changePasswordForm.value.newPwd,
        
      }

                this.apiService.changePasswd(param).subscribe({
                next: (result: any) => {
               
                console.log(result);
  
                this.alertService.showSuccess("password changed successfully","Success")
              
       
    },
    error: (err: any) => {
      
      console.log(err);
      this.alertService.showError("Failed to change password","Error")
    }  
    });
    }}}


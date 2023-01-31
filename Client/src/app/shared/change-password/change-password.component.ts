import { Component, OnInit } from '@angular/core';
import { AbstractControlOptions, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/api.service';
import { ConfirmPasswordValidator } from 'src/app/confirm-password.validator';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  changePasswordForm!:FormGroup
  constructor(private formbuilder:FormBuilder,private router:Router,private apiservice:ApiService) { 
    this.changePasswordForm=this.formbuilder.group({
      currentPass:['',[Validators.required,Validators.minLength(8),Validators.maxLength(25),Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9 \\!\"#\\$%&'\\(\\)\\*\\+,\\-\\.\\/\\:;\\<\\=\\>\\?@\\[\\\\\\]\\^_`\\{\\|\\}~]+$")]],
      newPass:['',[Validators.required,Validators.minLength(8),Validators.maxLength(25),Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9 \\!\"#\\$%&'\\(\\)\\*\\+,\\-\\.\\/\\:;\\<\\=\\>\\?@\\[\\\\\\]\\^_`\\{\\|\\}~]+$")]],
      newPass2:['',Validators.required]
        },
        {
          validator: ConfirmPasswordValidator("newPass", "newPass2")
        }as AbstractControlOptions );
  }
  ngOnInit(): void {
  }

}

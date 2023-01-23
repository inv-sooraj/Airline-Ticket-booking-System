import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!:FormGroup;
  status:any=false;
  listData:any;
  constructor(private formbuilder:FormBuilder,private router:Router,private apiservice:ApiService,private alertservice:AlertService) {

    this.loginForm=this.formbuilder.group({
      email:['',[Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password:['',[Validators.required,Validators.minLength(8),Validators.maxLength(25),Validators.pattern("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9 \\!\"#\\$%&'\\(\\)\\*\\+,\\-\\.\\/\\:;\\<\\=\\>\\?@\\[\\\\\\]\\^_`\\{\\|\\}~]+$")]]
      });
  }

  ngOnInit(): void {
  }
  onSubmit() {
    if (this.loginForm.valid) {

      let param ={
        "email": this.loginForm.value.email,
        "password":this.loginForm.value.password
      }
      this.apiservice.login(param).subscribe({
      next: (result: any) =>{
      this.listData = result.accessToken.value;
      //  this.EmpEmail = response.email;
      //  this.userRole=response.role;
      // localStorage.setItem('email', this.EmpEmail)
      localStorage.setItem('accessToken', this.listData)
      // localStorage.setItem('CurrentuserRole',this.userRole)
      // switch(result.role) {
        // case  1:
                    this.alertservice.showSuccess("Login Successful","Success");
                    this.status=true;
          // alert("Login Successfull")
      //     this.router.navigate(['/nav']);
      //     break;
      //   case  2:
      //     alert("Login Successfull")
      //     this.router.navigate(['/emp-nav']);
      //     break;
      //     case 3:
      //       // this.stdId = response.studentId;
      //       // this.stdName = response.firstName;
      //       // localStorage.setItem('StudentId', this.stdId)
      //       // localStorage.setItem('StudentName', this.stdName)
      //       alert("Login Successfull")
      //       this.router.navigate(['/stu-nav']);
      //     break;
      //   default:
      //     this.status=true;
      // } 
      },
      error: (err: any) => {
        this.alertservice.showError("Login Failed","Error");
        this.status=false;
        console.log(err);
      }  
      });
    }
    else
    {
      return;
    }
  }

}



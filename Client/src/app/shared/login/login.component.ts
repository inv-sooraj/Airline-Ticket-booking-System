import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/api.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!:FormGroup;
  status:any=false;
  constructor(private formbuilder:FormBuilder,private router:Router,private apiservice:ApiService) {

    this.loginForm=this.formbuilder.group({
      email:['',[Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password:['',[Validators.required,Validators.minLength(8),Validators.maxLength(25),Validators.pattern("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9 \\!\"#\\$%&'\\(\\)\\*\\+,\\-\\.\\/\\:;\\<\\=\\>\\?@\\[\\\\\\]\\^_`\\{\\|\\}~]+$")]]
      });
  }

  ngOnInit(): void {
  }
  onSubmit() {
    if (this.loginForm.valid) {
    this.apiservice.login(this.loginForm.value).subscribe({
      next: (result: any) =>{
      // this.listData = response.accessToken.value;
      //  this.EmpEmail = response.email;
      //  this.userRole=response.role;
      // localStorage.setItem('email', this.EmpEmail)
      // localStorage.setItem('accessToken', this.listData)
      // localStorage.setItem('CurrentuserRole',this.userRole)
      switch(result.role) {
        case  1:
          alert("Login Successfull")
          this.router.navigate(['/nav']);
          break;
        case  2:
          alert("Login Successfull")
          this.router.navigate(['/emp-nav']);
          break;
          case 3:
            // this.stdId = response.studentId;
            // this.stdName = response.firstName;
            // localStorage.setItem('StudentId', this.stdId)
            // localStorage.setItem('StudentName', this.stdName)
            alert("Login Successfull")
            this.router.navigate(['/stu-nav']);
          break;
        default:
          this.status=true;
      } 
      },
      error: (err: any) => {
        alert(err.name);
        console.log(err);
      }  
      });
    }
    else
    {
      this.status=true;
      return;
    }
  }

}



import { Component, OnInit } from '@angular/core';
import { AbstractControlOptions, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';
import { ConfirmPasswordValidator } from '../confirm-password.validator';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm!:FormGroup;
  isSubmitted = false;
  listData:any;
  roledata:any;
  country: any = ['USA', 'INDIA','CHINA','AUSTRALIA','DUBAI','JAPAN','SAUDI','CANADA'];
  status:any=false;
  constructor(private formbuilder:FormBuilder,private router:Router,private apiservice:ApiService) {
    this.listData = [];
    this.signupForm=this.formbuilder.group({
      fullname:['',[Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$"),Validators.minLength(8),Validators.maxLength(18)]],
      dob:['',Validators.required],
      email:['',[Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"),Validators.maxLength(30)]],
      password:['',[Validators.required,Validators.minLength(18),Validators.maxLength(25)]],
      cPassword:['',[Validators.required,Validators.minLength(18),Validators.maxLength(25)]],
      passportNo:['',[Validators.required,Validators.pattern("^[A-PR-WYa-pr-wy][1-9]\\d\\s?\\d{4}[1-9]$"),Validators.minLength(8),Validators.maxLength(8)]],
      phone:['',[Validators.required, Validators.pattern("^[0-9]*$"),Validators.minLength(10), Validators.maxLength(10)]],
      address:['',Validators.required],
      city:['',Validators.required],
      zipcode:['',Validators.required],
      country:['',Validators.required]
    // },
    // { validator: [ConfirmedValidator('password', 'cPassword')]
    //   // validator: ConfirmedValidator('password', 'cPassword')
    //     }as AbstractControlOptions
   
     },
     {
      validator: ConfirmPasswordValidator("password", "cPassword")
    }as AbstractControlOptions );
  }
  changeCountry(e:any) {
    console.log(e.value)
    this.country.setValue(e.target.value, {
      onlySelf: true
    })
  }
  register(){
    if(this.signupForm.valid){
      if(this.apiservice.isEmailUnique(this.signupForm.value.email)){
                this.apiservice.createUser(this.signupForm.value).subscribe({
                  next: (result: any) => {
                  alert('Created successfully')
                this.router.navigate(['/login'])  
       
    },
    error: (err: any) => {
      alert(err.name);
      console.log(err);
    }  
    });
    }
  }
    else{
        this.status=true;
    }
  }
  ngOnInit(): void {
  }
  // get f(){

  //   return this.signupForm.controls;

  // }

}

// function ConfirmedValidator(controlName: string, matchingControlName: string){
  

//     return (formGroup: FormGroup) => {

//         const control = formGroup.controls[controlName];

//         const matchingControl = formGroup.controls[matchingControlName];

//         if (matchingControl.errors && !matchingControl.errors['confirmedValidator']) {

//             return;

//         }

//         if (control.value !== matchingControl.value) {

//             matchingControl.setErrors({ confirmedValidator: true });

//         } else {

//             matchingControl.setErrors(null);

//         }

//     }
// }


import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-company-registration',
  templateUrl: './company-registration.component.html',
  styleUrls: ['./company-registration.component.css']
})
export class CompanyRegistrationComponent implements OnInit {
companyreg!:FormGroup;
status:any=false;
  constructor(private service:ServiceService,public router:Router) { }

  ngOnInit(): void {
    this.Initform();
    this.CompanyReg();
  }

  Initform(){
    this.companyreg = new FormGroup({
      companyname:new FormControl('',[Validators.required,Validators.pattern(/^[a-zA-Z0-9_]*$/), Validators.maxLength(10)]),
      companycontact:new FormControl('',[Validators.required]),
      companyaddress:new FormControl('',[Validators.required]),
      email:new FormControl('',[Validators.required,Validators.pattern(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)]),
      password:new FormControl('',[Validators.required,Validators.pattern(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,8}/), Validators.maxLength(8)]),
      conformpassword:new FormControl('',[Validators.required,Validators.pattern(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,8}/), Validators.maxLength(8)])
    });
  }

  // CompanyReg(){
  //   if(this.companyreg.valid){
  //     this.service.addcompany(this.companyreg.value).subscribe((result: any)=>{
  //       if(result)
  //       {
  //         console.log(result);
          
  //         alert('Success');
  //         this.companyreg.reset();
        
  //       }else{
  //         alert('False');
  //       }
  //     }

  //     )
  //   }
  // }


  CompanyReg()
  {
    
    if(this.companyreg.valid){
      console.log(this.companyreg.value);
      
      this.service.addcompany(this.companyreg.value).subscribe({
        next: (result: any) => {
        alert('Created successfully')
      //  this.router.navigate(['/login'])   
    },
    error: (err: any) => {
      alert(err.name);
      console.log(err);
    }  
    });
    }
    else{
        this.status=true;
    }
  }
  cancel()
  {
    this.companyreg.reset();
  }
}

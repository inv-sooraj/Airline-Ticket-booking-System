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
      email:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required]),
      conformpassword:new FormControl('',[Validators.required])
    });
  }

  CompanyReg(){
    if(this.companyreg.valid){
      this.service.addcompany(this.companyreg.value).subscribe((result: any)=>{
        if(result)
        {
          console.log(result);
          
          alert('Success');
          this.companyreg.reset();
        
        }else{
          alert('False');
        }
      }

      )
    }
  }
}

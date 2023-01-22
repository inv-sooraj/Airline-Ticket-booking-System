import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { range } from 'rxjs';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-company-registration',
  templateUrl: './company-registration.component.html',
  styleUrls: ['./company-registration.component.css']
})
export class CompanyRegistrationComponent implements OnInit {
  companyreg!: FormGroup;
  status: any = false;
  valid: Boolean = false;
  password = '';
  submitted:any;
  constructor(private service: ServiceService, public router: Router) { }

  ngOnInit(): void {
    this.Initform();
    this.CompanyReg();
    this.generatePassword();
  }

  public get Companys() { return this.companyreg?.controls; }

  Initform() {
    this.companyreg = new FormGroup({
      fullName: new FormControl('', [Validators.required,Validators.maxLength(50)]),
      phone: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.pattern(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)]),
      password: new FormControl({value:'',disabled:true } , [Validators.required])
    });
  }

  CompanyReg() {
    this.submitted=true;
    if (this.companyreg.valid) {
      this.valid = true;
      let param  = {
        "fullName":this.companyreg.controls['fullName'].value,
        "phone":this.companyreg.controls['phone'].value,
        "address":this.companyreg.controls['address'].value,
        "email":this.companyreg.controls['email'].value,
        "password":this.companyreg.controls['password'].value,
        "role":3,
        // "status":1

      }
      this.generatePassword();
     
      this.service.addcompany(param).subscribe({
        next: (result: any) => {
          alert('Created successfully')

        },
        error: (err: any) => {
          alert(err.error.error);
          console.log(err);
          this.valid = false;
        }
      });
    }
  }
  public generatePassword() {
    this.password = Math.random().toString(22).slice(6);
    this.companyreg.controls['password'].setValue(this.password);
  }
  cancel() {
    this.companyreg.reset();
  }

}




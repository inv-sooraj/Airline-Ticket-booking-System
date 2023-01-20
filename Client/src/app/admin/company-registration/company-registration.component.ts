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
  constructor(private service: ServiceService, public router: Router) { }

  ngOnInit(): void {
    this.Initform();
    this.CompanyReg();
    this.generatePassword();
  }

  Initform() {
    this.companyreg = new FormGroup({
      fullName: new FormControl('', [Validators.required,Validators.maxLength(50)]),
      phone: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.pattern(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)]),
      password: new FormControl('', [Validators.required])
    });
  }

  CompanyReg() {
    if (this.companyreg.valid) {
      this.valid = true;
      this.generatePassword();
      this.service.addcompany(this.companyreg.value).subscribe({
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




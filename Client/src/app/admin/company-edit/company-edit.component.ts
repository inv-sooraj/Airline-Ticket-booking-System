import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService} from 'ngx-toastr';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-company-edit',
  templateUrl: './company-edit.component.html',
  styleUrls: ['./company-edit.component.css']
})
export class CompanyEditComponent implements OnInit {
  companyupdate!:FormGroup;
  valid: Boolean = false;
  password = '';
  flag: any;
  constructor(private service:ServiceService,public toaster:ToastrService,public router:Router){ }

  ngOnInit(): void {

  this.InitUpdateform() ;
  }

  InitUpdateform() {
    this.companyupdate = new FormGroup({
      fullName: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]),
      phone: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]),
      address: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.pattern(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)]),
      password: new FormControl({ value: '', disabled: true }, [Validators.required])
    });
  }

  Companyupdate() {
    if (this.companyupdate.invalid) {

      this.flag = true;
    }
    else {
      (this.companyupdate.valid);
      {
        this.valid = true;
        let param = {
          "fullName": this.companyupdate.controls['fullName'].value,
          "phone": this.companyupdate.controls['phone'].value,
          "address": this.companyupdate.controls['address'].value,
          "email": this.companyupdate.controls['email'].value,
          "password": this.companyupdate.controls['password'].value,
          "role": 3,
          // "status":1

        }
        this.service.updatecompany(param).subscribe({
          next: (result: any) => {
            this.toaster.success('Created successfully', '');
            //alert('success');
            this.router.navigate(['/companylist']);

          },
          error: (err: any) => {
            this.toaster.error(err.error.error);
            console.log(err);
            this.valid = false;
          }
        });
      }
    }
  }

  cancel(){}
}

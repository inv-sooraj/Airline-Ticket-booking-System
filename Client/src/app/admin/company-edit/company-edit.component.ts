import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-company-edit',
  templateUrl: './company-edit.component.html',
  styleUrls: ['./company-edit.component.css']
})
export class CompanyEditComponent implements OnInit {
  companyupdate!: FormGroup;
  valid: Boolean = false;
  password = '';
  flag: any;
  userId: any;
  constructor(private service: ServiceService, public toaster: ToastrService, public router: Router, public activerouter: ActivatedRoute) { }

  ngOnInit(): void {

    this.InitUpdateform();

    this.activerouter.params.subscribe((param: any) => {
      this.userId = param['id'];
      this.getcompanyById();

    })

  }

  InitUpdateform() {
    this.companyupdate = new FormGroup({
      fullName: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]),
      phone: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(11)]),
      address: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.pattern(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)]),
      password: new FormControl({ value: '', disabled: true }, [Validators.required])
    });
  }

  getcompanyById() {
    this.service.getcompanyById(this.userId).subscribe({
      next: (result) => {
        this.userId = result.userId;
        this.companyupdate.controls['fullName'].setValue(result.fullName),
          this.companyupdate.controls['phone'].setValue(result.phone),
          this.companyupdate.controls['address'].setValue(result.address),
          this.companyupdate.controls['email'].setValue(result.email)
        console.log(result);
      },
      error: (err: any) => {
        this.toaster.error(err.error.error);
        console.log(err);
        this.valid = false;
      }
    });
  }
  Companyupdate() {
    if (this.companyupdate.invalid) {
      this.flag = true;
    }
    else {
      (this.companyupdate.valid)
      this.valid = true;
      this.service.updatecompany(this.companyupdate.value, this.userId).subscribe({
        next: (result: any) => {
          this.toaster.success('Created successfully', '');
          //alert('success');
          this.router.navigate(['/companylist']);

          console.log(result);
        },
        error: (err: any) => {
          this.toaster.error(err.error.error);
          console.log(err);
          this.valid = false;
        }
      });
    }
  }


  cancel() { }
}

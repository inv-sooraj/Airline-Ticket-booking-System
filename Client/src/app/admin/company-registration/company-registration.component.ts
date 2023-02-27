import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";
import { ServiceService } from "src/app/service.service";

@Component({
  selector: "app-company-registration",
  templateUrl: "./company-registration.component.html",
  styleUrls: ["./company-registration.component.css"],
})
export class CompanyRegistrationComponent implements OnInit {
  //   companyreg!: FormGroup;
  //   constructor(private service: ServiceService, public router: Router) {}

  //   ngOnInit(): void {
  //     this.Initform();
  //     this.CompanyReg();
  //   }

  //   Initform() {
  //     this.companyreg = new FormGroup({
  //       companyname: new FormControl("", [
  //         Validators.required,
  //         Validators.pattern("^[a-zA-Z][a-zA-Z ]+$"),
  //         Validators.minLength(8),
  //         Validators.maxLength(18),
  //       ]),
  //       companycontact: new FormControl("", [
  //         Validators.required,
  //         Validators.pattern("^[0-9]*$"),
  //         Validators.minLength(10),
  //         Validators.maxLength(10),
  //       ]),
  //       companyaddress: new FormControl("", [Validators.required]),
  //       email: new FormControl("", [
  //         Validators.required,
  //         Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"),
  //         Validators.maxLength(30),
  //       ]),
  //       password: new FormControl("", [
  //         Validators.required,
  //         Validators.minLength(8),
  //         Validators.maxLength(25),
  //         Validators.pattern(
  //           "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-zd$@$!%*?&].{8,}"
  //         ),
  //       ]),
  //       conformpassword: new FormControl("", [Validators.required]),
  //     });
  //   }

  //   CompanyReg() {
  //     if (this.companyreg.valid) {
  //       this.service
  //         .addcompany(this.companyreg.value)
  //         .subscribe((result: any) => {
  //           if (result) {
  //             console.log(result);

  //             alert("Success");
  //             this.companyreg.reset();
  //           } else {
  //             alert("False");
  //           }
  //         });
  //     }
  //   }
  // }
  companyreg!: FormGroup;
  status: any = false;
  password = "";
  flag: any;
  constructor(
    private service: ApiService,
    public router: Router,
    private toaster: ToastrService,
    private alertservice: AlertService
  ) {}

  ngOnInit(): void {
    this.Initform();
    this.generatePassword();
    this.flag = false;
  }

  public get Companys() {
    return this.companyreg?.controls;
  }

  Initform() {
    this.companyreg = new FormGroup({
      fullName: new FormControl("", [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(50),
      ]),
      phone: new FormControl("", [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(10),
      ]),
      address: new FormControl("", [Validators.required]),
      email: new FormControl("", [
        Validators.required,
        Validators.pattern(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/),
      ]),
      password: new FormControl({ value: "", disabled: true }, [
        Validators.required,
      ]),
    });
  }
  public generatePassword() {
    this.password = Math.random().toString(22).slice(6);
    this.companyreg.controls["password"].setValue(this.password);
  }
  cancel() {
    this.companyreg.reset();
  }
  CompanyReg() {
    if (this.companyreg.valid) {
      {
        let param = {
          fullName: this.companyreg.controls["fullName"].value,
          phone: this.companyreg.controls["phone"].value,
          address: this.companyreg.controls["address"].value,
          email: this.companyreg.controls["email"].value,
          password: this.companyreg.controls["password"].value,
          role: 2,
        };
        this.generatePassword();
        this.service.createCompany(param).subscribe({
          next: (result: any) => {
            this.toaster.success("Created successfully", "");
            //alert('success');
            this.router.navigate(["/company-list"]);
          },
          error: (err: any) => {
            this.toaster.error(err.error.error);
            console.log(err);
            // this.valid = false;
          },
        });
      }
    } else {
      this.alertservice.showError(
        "Please fill the form correctly",
        "Invalid form"
      );
    }
  }
}

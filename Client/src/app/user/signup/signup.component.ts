import { Component, OnInit } from "@angular/core";
import {
  AbstractControlOptions,
  FormBuilder,
  FormGroup,
  Validators,
} from "@angular/forms";
import { Router } from "@angular/router";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";
import { ConfirmPasswordValidator } from "src/app/confirm-password.validator";
@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.css"],
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;
  isSubmitted = false;
  listData: any;
  roledata: any;
  country: any = [
    "USA",
    "INDIA",
    "CHINA",
    "AUSTRALIA",
    "DUBAI",
    "JAPAN",
    "SAUDI",
    "CANADA",
  ];
  status: any = false;
  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private apiservice: ApiService,
    private alertservice: AlertService
  ) {
    this.listData = [];
    this.signupForm = this.formbuilder.group(
      {
        fullname: [
          "",
          [
            Validators.required,
            Validators.pattern("^[a-zA-Z][a-zA-Z ]+$"),
            Validators.minLength(8),
            Validators.maxLength(18),
          ],
        ],
        dob: ["", Validators.required],
        email: [
          "",
          [
            Validators.required,
            Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"),
            Validators.maxLength(30),
          ],
        ],
        password: [
          "",
          [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(25),
            Validators.pattern(
              "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-zd$@$!%*?&].{8,}"
            ),
          ],
        ],
        cPassword: [
          "",
          [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(25),
          ],
        ],
        passportNo: [
          "",
          [
            Validators.required,
            Validators.pattern("^[A-PR-WYa-pr-wy][1-9]\\d\\s?\\d{4}[1-9]$"),
            Validators.minLength(8),
            Validators.maxLength(8),
          ],
        ],
        phone: [
          "",
          [
            Validators.required,
            Validators.pattern("^[0-9]*$"),
            Validators.minLength(10),
            Validators.maxLength(10),
          ],
        ],
        address: ["", Validators.required],
        city: ["", Validators.required],
        country: ["", Validators.required],
      },
      {
        validator: ConfirmPasswordValidator("password", "cPassword"),
      } as AbstractControlOptions
    );
  }
  ngOnInit(): void {
    this.getToday();
  }
  changeCountry(e: any) {
    console.log(e.value);
    this.country.setValue(e.target.value, {
      onlySelf: true,
    });
  }
  disableDate() {
    return false;
  }
  register() {
    if (this.signupForm.valid) {
      let param = {
        fullName: this.signupForm.value.fullname,
        email: this.signupForm.value.email,
        password: this.signupForm.value.password,
        dob: this.signupForm.value.dob,
        passportNumber: this.signupForm.value.passportNo,
        address: this.signupForm.value.address,
        city: this.signupForm.value.city,
        phone: this.signupForm.value.phone,
        country: this.signupForm.value.country,
        status: 1,
        role: 3,
      };
      this.apiservice.createUser(param).subscribe({
        next: (result: any) => {
          this.status = true;
          console.log(result);

          this.alertservice.showSuccess("Signed up successfully", "Success");
          this.router.navigate(["/login"]);
        },
        error: (err: any) => {
          this.status = false;
          console.log(err);
          this.alertservice.showError("Failed to signup", "Error");
        },
      });
    } else {
      this.alertservice.showError(
        "Please fill the form correctly",
        "Invalid form"
      );
    }
  }
  getToday(){
    return new Date().toISOString().split('T')[0];
  }
  
}
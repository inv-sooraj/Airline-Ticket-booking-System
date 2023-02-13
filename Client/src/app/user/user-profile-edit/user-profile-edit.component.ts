import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";

@Component({
  selector: "app-user-profile-edit",
  templateUrl: "./user-profile-edit.component.html",
  styleUrls: ["./user-profile-edit.component.css"],
})
export class UserProfileEditComponent {
  profileEditForm!: FormGroup;
  userid: any;
  items: any;
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
  constructor(
    private formbuilder: FormBuilder,
    private apiservice: ApiService,
    private alertservice: AlertService
  ) {
    this.userid = localStorage.getItem("userid");
    console.log("current user id", this.userid);

    this.profileEditForm = this.formbuilder.group({
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
        ],
      ],
      newpassword: [
        "",
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(25),
          Validators.pattern(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9 \\!\"#\\$%&'\\(\\)\\*\\+,\\-\\.\\/\\:;\\<\\=\\>\\?@\\[\\\\\\]\\^_`\\{\\|\\}~]+$"
          ),
        ],
      ],
      confirmPassword: [
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
    });
  }
  ngOnInit(): void {
    this.getUserData();
  }

  /**To fetch the user data of the spacific id from db */

  getUserData() {
    this.apiservice.getUserById(this.userid).subscribe({
      next: (response: any) => {
        this.items = response;
        console.log("user details rsponse", this.items);
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to load user details", "Error");
      },
      complete: () => {},
    });
  }
  changeCountry(e: any) {
    console.log(e.target.value);
    this.country.setValue(e.target.value, {
      onlySelf: true,
    });
  }
  editUser() {
    let param = {
      fullName: this.profileEditForm.value.fullname,
      email: this.profileEditForm.value.email,
      dob: this.profileEditForm.value.dob,
      passportNumber: this.profileEditForm.value.passportNo,
      address: this.profileEditForm.value.address,
      city: this.profileEditForm.value.city,
      phone: this.profileEditForm.value.phone,
      country: this.profileEditForm.value.country,
    };
    this.apiservice.editUserData(param).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess(
          "Details Updated successfully!!!",
          "Success"
        );
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to update details", "Error");
      },
      complete: () => {},
    });
  }
}

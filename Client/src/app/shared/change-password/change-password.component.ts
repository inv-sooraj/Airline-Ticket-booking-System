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
  selector: "app-change-password",
  templateUrl: "./change-password.component.html",
  styleUrls: ["./change-password.component.css"],
})
export class ChangePasswordComponent implements OnInit {
  changePasswordForm!: FormGroup;
  status: any = false;
  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private apiservice: ApiService,
    private alertservice: AlertService
  ) {
    this.changePasswordForm = this.formbuilder.group(
      {
        currentPass: [
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
        newPass: [
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
        newPass2: ["", Validators.required],
      },
      {
        validator: ConfirmPasswordValidator("newPass", "newPass2"),
      } as AbstractControlOptions
    );
  }
  ngOnInit(): void {}
  changePwd() {
    if (this.changePasswordForm.valid) {
      let param = {
        currentPwd: this.changePasswordForm.value.currentPass,
        newPwd: this.changePasswordForm.value.newPass,
      };

      this.apiservice.changePasswd(param).subscribe({
        next: (response: any) => {
          this.status = true;
          this.alertservice.showSuccess("Password is changed!!!", "Success");
          localStorage.clear();
          this.router.navigate(["/login"]);
        },
        error: (err: any) => {
          this.status = false;
          console.log(err);
          console.log(err.error.errors[0].code);
          switch (err.error.errors[0].code) {
            case "104":
              this.alertservice.showError(
                "Current password and new password are same",
                "Error"
              );
              break;
            case "105":
              this.alertservice.showError("Current password is wrong", "Error");
              break;
          }
        },
        complete: () => {},
      });
    } else {
      this.alertservice.showError(
        "please fill the form currectly",
        "Invalid form"
      );
    }
  }
}

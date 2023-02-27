import { Component, OnInit } from "@angular/core";
import {
  AbstractControlOptions,
  Form,
  FormBuilder,
  FormGroup,
  Validators,
} from "@angular/forms";
import { Router } from "@angular/router";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";
import { ConfirmPasswordValidator } from "src/app/confirm-password.validator";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"],
})
export class HeaderComponent implements OnInit {
  changePassowrdForm!: FormGroup;
  constructor(
    private formbuilder: FormBuilder,
    private apiService: ApiService,
    private alertService: AlertService,
    private router: Router
  ) {}

  ngOnInit(): void {}
  changePasswordForm = this.formbuilder.group(
    {
      currentPwd: [
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
      newPwd: [
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
      cPwd: ["", Validators.required],
    },
    {
      validator: ConfirmPasswordValidator("newPwd", "cPwd"),
    } as AbstractControlOptions
  );
  register() {
    if (this.changePasswordForm.valid) {
      let param = {
        currentPwd: this.changePasswordForm.value.currentPwd,
        newPwd: this.changePasswordForm.value.newPwd,
      };

      this.apiService.changePasswd(param).subscribe({
        next: (result: any) => {
          console.log(result);

          this.alertService.showSuccess(
            "password changed successfully",
            "Success"
          );
          localStorage.clear();
          this.router.navigate(["/login"]);
        },
        error: (err: any) => {
          console.log(err);
          console.log(err.error.errors[0].code);
          switch (err.error.errors[0].code) {
            case "104":
              this.alertService.showError(
                "Current password and new password are same",
                "Error"
              );
              break;
            case "105":
              this.alertService.showError("Current password is wrong", "Error");
              break;
          }
        },
      });
    }
  }
}

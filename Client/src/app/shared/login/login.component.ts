import { ThisReceiver } from "@angular/compiler";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";
@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  status: any = false;
  listData: any;
  role: any;
  userid: any;
  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private apiservice: ApiService,
    private alertservice: AlertService
  ) {
    this.loginForm = this.formbuilder.group({
      email: [
        "",
        [
          Validators.required,
          Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"),
        ],
      ],
      password: [
        "",
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(20),
          Validators.pattern(
            "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-zd$@$!%*?&].{8,}"
          ),
        ],
      ],
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.loginForm.valid) {
      let param = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password,
      };
      this.apiservice.login(param).subscribe({
        next: (result: any) => {
          this.listData = result.accessToken.value;
          this.role = result.role;
          this.userid = result.userId;
          localStorage.setItem("accessToken", this.listData);
          localStorage.setItem("Role", this.role);
          localStorage.setItem("userid", this.userid);
          console.log("Role login", this.role);
          console.log("access token =" + localStorage.getItem("accessToken"));
          console.log("user id = "+localStorage.getItem("userid"));
          this.status = true;
          switch (result.role) {
            /**For admin */
            case 1:
              this.alertservice.showSuccess("Login Successful", "Success");
              this.router.navigate(["/plane-list"]);
              break;
            /**For Company */
            case 2:
              this.alertservice.showSuccess("Login Successful", "Success");
              this.router.navigate(["/plane-list"]);
              break;
            /**For passenger */
            case 3:
              this.alertservice.showSuccess("Login Successful", "Success");
              this.router.navigate(["/home"]);
              break;
            default:
              this.alertservice.showError("Login Failed", "Login Error");
          }
        },
        error: (err: any) => {
          this.status = false;
          this.alertservice.showError("Login Failed", "Error");
          console.log(err);
        },
      });
    } else {
      return;
    }
  }
}

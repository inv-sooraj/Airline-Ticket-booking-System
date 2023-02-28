import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";

@Component({
  selector: "app-user-detail",
  templateUrl: "./user-detail.component.html",
  styleUrls: ["./user-detail.component.css"],
})
export class UserDetailComponent implements OnInit {
  // userEditForm!:FormGroup
  data: any;
  userId = -1;
  constructor(
    private formbuilder: FormBuilder,
    private apiservice: ApiService,
    private router: Router,
    private route: ActivatedRoute,
    private alertservice: AlertService
  ) {}

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      this.userId = params["userId"];
    });
    this.getData();
  }
  getData() {
    this.apiservice.getUserById(this.userId).subscribe({
      next: (response: any) => {
        this.data = response;
        console.log("Editing details", response);
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to load user details", "Error");
      },
      complete: () => {},
    });
  }
  deleteAccount() {
    this.apiservice.deleteUser(this.userId).subscribe({
      next: (response: any) => {
        this.data = response;
        this.alertservice.showSuccess("Deletion Success", "success");
        this.router.navigate(["/user-list"]);
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to delete user", "Error");
      },
      complete: () => {},
    });
  }
  cancel() {
    this.router.navigate(["/user-list"]);
  }
}

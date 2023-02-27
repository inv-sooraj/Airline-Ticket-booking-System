import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { AlertService } from "../alert.service";
import { ApiService } from "../api.service";

@Component({
  selector: "app-company-edit",
  templateUrl: "./company-edit.component.html",
  styleUrls: ["./company-edit.component.css"],
})
export class CompanyEditComponent implements OnInit {
  companyEditForm!: FormGroup;
  companyId: any;
  CompanyById: any;
  constructor(
    private formbuilder: FormBuilder,
    private apiservice: ApiService,
    private router: Router,
    private route: ActivatedRoute,
    private alertservice: AlertService
  ) {
    this.companyEditForm = this.formbuilder.group({
      fullName: [
        "",
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50),
        ],
      ],
      phone: [
        "",
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(10),
        ],
      ],
      address: ["", [Validators.required]],
      email: [
        "",
        [
          Validators.required,
          Validators.pattern(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/),
        ],
      ],
    });
  }
  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      this.companyId = params["userId"];
    });
    this.getCompanyById();
  }

  getCompanyById() {
    this.apiservice.getUserById(this.companyId).subscribe({
      next: (response: any) => {
        this.CompanyById = response;
        console.log("Company details", this.CompanyById);
      },
      error: (err: any) => {},
      complete: () => {},
    });
  }

  companyUpdate() {
    this.apiservice
      .updateCompany(this.companyId, this.companyEditForm.value)
      .subscribe({
        next: (response: any) => {
          this.alertservice.showSuccess("Updated successfully", "Success");
          this.router.navigate(["/company-list"]);
        },
        error: (err: any) => {
          this.alertservice.showError(
            "Failed to updaet data.Please try again",
            "Failed"
          );
        },
        complete: () => {},
      });
  }
  cancel() {
    this.router.navigate(["/company-list"]);
  }
}

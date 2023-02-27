<<<<<<< HEAD
import { DOCUMENT } from "@angular/common";
import { Component, Inject, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";
@Component({
  selector: "app-airplane-reg",
  templateUrl: "./airplane-reg.component.html",
  styleUrls: ["./airplane-reg.component.css"],
})
export class AirplaneRegComponent implements OnInit {
  planeRegForm!: FormGroup;
  status: any = false;
  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private apiservice: ApiService,
    @Inject(DOCUMENT) document: Document,
    private alertservice: AlertService
  ) {
    this.planeRegForm = this.formbuilder.group({
      airplaneName: [
        "",
        [
          Validators.required,
          Validators.pattern("^[a-zA-Z0-9]*$"),
          Validators.minLength(5),
          Validators.maxLength(30),
        ],
      ],
      modelNo: [
        "",
        [
          Validators.required,
          Validators.pattern("^[a-zA-Z0-9]*$"),
          Validators.minLength(5),
          Validators.maxLength(30),
        ],
      ],
      totalSeats: [
        "",
        [
          Validators.required,
          Validators.pattern("^[0-9]*$"),
          Validators.maxLength(9),
          Validators.maxLength(9),
        ],
      ],
    });
  }

  ngOnInit(): void {}
  /**Method to add plane details to db */

  addPlane() {
    if (this.planeRegForm.valid) {
      this.apiservice.createPlane(this.planeRegForm.value).subscribe({
        next: (result: any) => {
          this.alertservice.showSuccess(
            "Airplane details added successfully",
            "success"
          );
          this.status = true;
          this.router.navigate(["/plane-list"]);
        },
        error: (err: any) => {
          this.alertservice.showError(
            "Failed to add airplane details",
            "Failed"
          );
          this.status = false;
        },
      });
    } else {
      this.alertservice.showError(
        "Please fill the details correctly!!",
        "Invalid form"
      );
    }
  }
  cancel() {
    this.planeRegForm.reset();
  }
}
=======
import { DOCUMENT } from "@angular/common";
import { Component, Inject, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";
@Component({
  selector: "app-airplane-reg",
  templateUrl: "./airplane-reg.component.html",
  styleUrls: ["./airplane-reg.component.css"],
})
export class AirplaneRegComponent implements OnInit {
  planeRegForm!: FormGroup;
  status: any = false;
  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private apiservice: ApiService,
    @Inject(DOCUMENT) document: Document,
    private alertservice: AlertService
  ) {
    this.planeRegForm = this.formbuilder.group({
      airplaneName: [
        "",
        [
          Validators.required,
          Validators.pattern("^[a-zA-Z0-9]*$"),
          Validators.minLength(5),
          Validators.maxLength(30),
        ],
      ],
      modelNo: [
        "",
        [
          Validators.required,
          Validators.pattern("^[a-zA-Z0-9]*$"),
          Validators.minLength(5),
          Validators.maxLength(30),
        ],
      ],
      totalSeats: [
        "",
        [
          Validators.required,
          Validators.pattern("^[0-9]*$"),
          Validators.maxLength(9),
          Validators.maxLength(9),
        ],
      ],
    });
  }

  ngOnInit(): void {}
  /**Method to add plane details to db */

  addPlane() {
    if (this.planeRegForm.valid) {
      this.apiservice.createPlane(this.planeRegForm.value).subscribe({
        next: (result: any) => {
          this.alertservice.showSuccess(
            "Airplane details added successfully",
            "success"
          );
          this.status = true;
          this.router.navigate(["/plane-list"]);
        },
        error: (err: any) => {
          this.alertservice.showError(
            "Failed to add airplane details",
            "Failed"
          );
          this.status = false;
        },
      });
    } else {
      this.alertservice.showError(
        "Please fill the details correctly!!",
        "Invalid form"
      );
    }
  }
  cancel() {
    this.planeRegForm.reset();
  }
}


>>>>>>> 4811df5aacee65ea25f7b2324b95b1b61b76d091

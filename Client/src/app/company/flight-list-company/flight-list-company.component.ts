import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-flight-list-company',
  templateUrl: './flight-list-company.component.html',
  styleUrls: ['./flight-list-company.component.css']
})
export class FlightListCompanyComponent implements OnInit {

  FlightListForm!: FormGroup;
  searchText: any;
  itemName: any;
  website: any[] = [];
  items: any[] = [];
  Company: any;
  public data: any;
  public searchData: any[] = [];
  parentSelector: boolean = false;
  status: any = false;
  role: any;
  userid: any;
  constructor(
    private formbuilder: FormBuilder,
    private apiservice: ApiService,
    private alertservice: AlertService
  ) {
    this.FlightListForm = this.formbuilder.group({
      search: [""],
      company: [""],
      sel: this.formbuilder.array([]),
    });
    this.role = localStorage.getItem("Role");
    this.userid = localStorage.getItem("userid");
    if (this.role == "1") {
      this.status = true;
    }
  }

  /**For storing the id of selected airplanes in formarray */

  onCheckboxChange(e: any) {

    if (e.target.checked) {
      this.website.push(e.target.value);
      console.log("Arrayyyy " + this.website);
    } else {
      const index = this.website.indexOf(e.target.value);
      this.website.splice(index, 1);
      console.log("Array after unchecked", this.website);
    }
  }

  /**For selecting company from dropdown list(for admin only) */

  changeCompany() {
    this.apiservice.getFlightByCompany(this.FlightListForm.value.company).subscribe({
      next: (response: any) => {
        this.items = response;
        console.log("flight by  company", this.items);
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to load flight data", "Error");
      },
      complete: () => {},
    });
  }

  
  ngOnInit(): void {
    this.getFlight();
    this.getCompanyName();
  }

  /**To get company name from the user table */

  getCompanyName() {
    this.apiservice.getCompany().subscribe({
      next: (response: any) => {
        this.Company = response;
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch company details", "error");
      },
      complete: () => {},
    });
  }

  /**To get all airplane details */

  getFlight() {
    /* for  admin */
    if (this.role == "1") {
      this.apiservice.getAllFlight().subscribe({
        next: (response: any) => {
          this.items = response;
          console.log(this.items);
        },
        error: (err: any) => {
          this.alertservice.showError("Failed to load flight data", "Error");
        },
        complete: () => {},
      });
      /* for  company */
    } else if (this.role == "2") {
      this.apiservice.getFlightByCompany(this.userid).subscribe({
        next: (response: any) => {
          this.items = response;
          console.log("flight by  company", this.items);
        },
        error: (err: any) => {
          this.alertservice.showError("Failed to load flight data", "Error");
        },
        complete: () => {},
      });
    } else {
      this.alertservice.showError("Access Denied", "Error");
    }
  }

  /**To delete the selected airplanes */

  deleteData() {
    this.apiservice.deleteFlight(this.website).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess("Deleted Successfully!!!", "success");
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to delete!!!", "error");
      },
      complete: () => {
        this.getFlight();
      },
    });
  }
}


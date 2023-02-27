<<<<<<< HEAD
import { Component, OnInit } from "@angular/core";
import { FormArray, FormBuilder, FormControl, FormGroup } from "@angular/forms";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";
import { BookingServiceService } from "../services/booking-service.service";
@Component({
  selector: "app-reservation-list",
  templateUrl: "./reservation-list.component.html",
  styleUrls: ["./reservation-list.component.css"],
})
export class ReservationListComponent implements OnInit {
  items: any[] = [];
  site: any[] = [];
  bookingListForm!: FormGroup;
  searchText: any;
  role: any;
  public data: any;
  status: boolean = false;
  constructor(
    private formbuilder: FormBuilder,
    private alertservice: AlertService,
    private bookingService: BookingServiceService
  ) {
    this.bookingListForm = this.formbuilder.group({
      search: [""],
      sel: this.formbuilder.array([]),
    });
  }
  ngOnInit(): void {
    this.role = localStorage.getItem("Role");
    console.log("current user role", this.role);

    this.getBookings();
  }

  approve(id: any, status: any) {
    this.bookingService.changeStatus(id, status).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess(
          "Status changed Successfully!!!",
          "success"
        );
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to Change Status!!!", "error");
        console.log("error message" + err);
      },
      complete: () => {
        this.getBookings();
      },
    });
  }
  //delete booking details of selected ids
  deleteData() {
    this.bookingService.deletebooking(this.site).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess("Deleted Successfully!!!", "success");
        console.log("response = " + response);
        this.getBookings();
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to delete!!!", "error");
        console.log("error message" + err);
      },
      complete: () => {
        this.getBookings();
      },
    });
  }

  onCheckboxChange(e: any) {
    //store ids of selected booking details
    if (e.target.checked) {
      this.site.push(e.target.value);
      console.log("ids are : " + this.site);
    } else {
      const index = this.site.indexOf(e.target.value);
      this.site.splice(index, 1);
      console.log("Array after unchecked", this.site);
    }
  }

  getBookings() {
    console.log("inside get", this.role);

    switch (this.role) {
      case "1": //for admin
        this.bookingService.getBooking().subscribe({
          next: (response: any) => {
            this.items = response;
            console.log(this.items);
          },
          error: (err: any) => {
            this.alertservice.showError(
              "Failed to load airplane data",
              "Error"
            );
          },
          complete: () => {},
        });
        break;
      case "2": //for company
        this.bookingService.getBookingByCompany().subscribe({
          next: (response: any) => {
            this.items = response;
            console.log("Bookngs by  company", this.items);
          },
          error: (err: any) => {
            this.alertservice.showError("Failed to load booking data", "Error");
          },
          complete: () => {},
        });
        break;
      default:
        this.alertservice.showError("Access Denied", "Error");
        break;
    }
  }

  export() {
    console.log("export function");
    window.open("http://localhost:9091/bookings/download");
  }
}
=======
import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormArray, FormBuilder, FormControl, FormGroup } from "@angular/forms";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";
import { BookingServiceService } from "../services/booking-service.service";
@Component({
  selector: "app-reservation-list",
  templateUrl: "./reservation-list.component.html",
  styleUrls: ["./reservation-list.component.css"],
})
export class ReservationListComponent implements OnInit {
  items: any[] = [];
  site: any[] = [];
  bookingListForm!: FormGroup;
  searchText: any;
  role: any;
  public data: any;
  status: boolean = false;
  constructor(
    private formbuilder: FormBuilder,
    private alertservice: AlertService,
    private bookingService: BookingServiceService,private http: HttpClient
  ) {
    this.bookingListForm = this.formbuilder.group({
      search: [""],
      sel: this.formbuilder.array([]),
    });
  }
  ngOnInit(): void {
    this.role = localStorage.getItem("Role");
    console.log("current user role", this.role);

    this.getBookings();
  }

  approve(id: any, status: any) {
    this.bookingService.changeStatus(id, status).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess(
          "Status changed Successfully!!!",
          "success"
        );
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to Change Status!!!", "error");
        console.log("error message" + err);
      },
      complete: () => {
        this.getBookings();
      },
    });
  }
//delete booking details of selected ids
  deleteData() {
    this.bookingService.deletebooking(this.site).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess("Deleted Successfully!!!", "success");
        console.log("response = " + response);
        this.getBookings();
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to delete!!!", "error");
        console.log("error message" + err);
      },
      complete: () => {
        this.getBookings();
      },
    });
  }

  onCheckboxChange(e: any) {//store ids of selected booking details
    if (e.target.checked) {
      this.site.push(e.target.value);
      console.log("ids are : " + this.site);
    } else {
      const index = this.site.indexOf(e.target.value);
      this.site.splice(index, 1);
      console.log("Array after unchecked", this.site);
    }
  }

  getBookings() {
    console.log("inside get", this.role);

    switch (this.role) {
      case "1"://for admin
        this.bookingService.getBooking().subscribe({
          next: (response: any) => {
            this.items = response;
            console.log(this.items);
          },
          error: (err: any) => {
            this.alertservice.showError(
              "Failed to load airplane data",
              "Error"
            );
          },
          complete: () => {},
        });
        break;
      case "2"://for company
        this.bookingService.getBookingByCompany().subscribe({
          next: (response: any) => {
            this.items = response;
            console.log("Bookngs by  company", this.items);
          },
          error: (err: any) => {
            this.alertservice.showError("Failed to load booking data", "Error");
          },
          complete: () => {},
        });
        break;
      default:
        this.alertservice.showError("Access Denied", "Error");
        break;
    }
  }

  export() {
    console.log("Export");
    
    // console.log("export function");
    // window.open("http://localhost:9091/bookings/download");
    this.http.get('http://localhost:9091/bookings/download', { responseType: 'blob' })
      .subscribe(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'my-csv-file.csv';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
      });
  }
  
}
>>>>>>> 4811df5aacee65ea25f7b2324b95b1b61b76d091

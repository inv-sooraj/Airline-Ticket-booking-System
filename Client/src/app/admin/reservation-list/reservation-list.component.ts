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
  constructor(private http:HttpClient,
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
      },
      complete: () => {
        this.getBookings();
      },
    });
  }
  deleteData() {
    this.bookingService.deletebooking(this.site).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess("Deleted Successfully!!!", "success");
        this.getBookings();
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to delete!!!", "error");
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
    } else {
      const index = this.site.indexOf(e.target.value);
      this.site.splice(index, 1);
    }
  }

  getBookings() {
    switch (this.role) {
      case "1": //for admin
        this.bookingService.getBooking().subscribe({
          next: (response: any) => {
            this.items = response;
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

    this.http
      .get("http://localhost:9091/bookings/download", { responseType: "blob" })
      .subscribe((blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = 'reservation-list.csv';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
      });
  }
  
}

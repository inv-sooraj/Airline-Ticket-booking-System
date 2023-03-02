import { Component, OnInit } from "@angular/core";
import { FormBuilder } from "@angular/forms";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { BookingServiceService } from "../admin/services/booking-service.service";
import { AlertService } from "../alert.service";
import { ApiService } from "../api.service";

@Component({
  selector: "app-reservation-details",
  templateUrl: "./reservation-details.component.html",
  styleUrls: ["./reservation-details.component.css"],
})
export class ReservationDetailsComponent implements OnInit {
  bookingId = -1;
  response: any;
  constructor(
    private formbuilder: FormBuilder,
    private apiservice: ApiService,
    private router: Router,
    private route: ActivatedRoute,
    private alertservice: AlertService,
    private bookingService: BookingServiceService
  ) {}
  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      this.bookingId = params["bookingId"];
    });
    this.getBookingDetails();
  }
  //method to get bookingdetails of a particular bookingid
  getBookingDetails() {
    this.apiservice.bookingDetailsById(this.bookingId).subscribe({
      next: (response: any) => {
        this.response = response;
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to load user booking", "Error");
      },
      complete: () => {},
    });
  }
  //method to change status (approve/reject) of booking
  approve(id: any, status: any) {
    this.bookingService.changeStatus(id, status).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess(
          "Status changed Successfully!!!",
          "success"
        );
        this.router.navigate(["/pending-list"]);
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to Change Status!!!", "error");
      },
      complete: () => {},
    });
  }
}

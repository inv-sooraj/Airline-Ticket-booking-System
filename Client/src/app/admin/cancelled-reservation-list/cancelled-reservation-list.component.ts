import { Component } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { AlertService } from "src/app/alert.service";
import { BookingServiceService } from "../services/booking-service.service";

@Component({
  selector: "app-cancelled-reservation-list",
  templateUrl: "./cancelled-reservation-list.component.html",
  styleUrls: ["./cancelled-reservation-list.component.css"],
})
export class CancelledReservationListComponent {
  items: any;
  itemName: any;
  cancelledListForm!: FormGroup;
  public searchData: any[] = [];
  searchText: any;
  checkList: any[] = [];
  constructor(
    private formbuilder: FormBuilder,
    private alertservice: AlertService,
    private bookingService: BookingServiceService
  ) {}
  ngOnInit(): void {
    this.getCancelledBookings();
    this.cancelledListForm = this.formbuilder.group({
      search: [""],
    });
  }
  Search() {
    console.log(this.itemName);
    if (this.itemName == "") {
      this.getCancelledBookings();
    } else {
      this.searchData = this.searchData.filter((res) => {
        return res.itemName
          .toLocaleLowerCase()
          .match(this.itemName.toLocaleLowerCase());
      });
    }
  }
  getCancelledBookings() {
    this.bookingService.getBooking().subscribe({
      next: (response: any) => {
        this.items = response;
        console.log(typeof this.items);
      },
      error: (err: any) => {
        this.alertservice.showError(
          "Failed to load cancelled bookings",
          "Error"
        );
      },
      complete: () => {},
    });
  }
  onCheckboxChange(e: any) {
    var index = this.checkList.indexOf(e.target.value);
    if (index === -1) {
      this.checkList.push(e.target.value);
      console.log("ids are : " + this.checkList);
    } else {
      this.checkList.splice(index, 1);
      console.log("ids are : " + this.checkList);
    }
  }

  deleteData() {
    this.bookingService.deletebooking(this.checkList).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess("Deleted Successfully!!!", "success");
        console.log("response = " + response);
        this.getCancelledBookings();
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to delete!!!", "error");
        console.log("error message" + err);
      },
      complete: () => {
        this.getCancelledBookings();
      },
    });
  }
  export() {
    console.log("export function");
    // this.bookingService.download().subscribe();
    window.open("http://localhost:9091/bookings/download/3");
  }
}

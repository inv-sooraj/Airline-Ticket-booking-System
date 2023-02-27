import { HttpClient } from "@angular/common/http";
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
    private formbuilder: FormBuilder,private http: HttpClient,
    private alertservice: AlertService,
    private bookingService: BookingServiceService
  ) {}
  ngOnInit(): void {
    this.getPendingBookings();
    this.cancelledListForm = this.formbuilder.group({
      search: [""],
    });
  }
  Search() {
    console.log(this.itemName);
    if (this.itemName == "") {
      this.getPendingBookings();
    } else {
      this.searchData = this.searchData.filter((res) => {
        return res.itemName
          .toLocaleLowerCase()
          .match(this.itemName.toLocaleLowerCase());
      });
    }
  }
  getPendingBookings() {
    this.bookingService.getPending().subscribe({
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
  onCheckboxChange(e: any) {//store ids of selected booking details
    if (e.target.checked) {
      this.checkList.push(e.target.value);
      console.log("ids are : " + this.checkList);
    } else {
      const index = this.checkList.indexOf(e.target.value);
      this.checkList.splice(index, 1);
      console.log("Array after unchecked", this.checkList);
    }
  }
 
//delete booking details of selected ids
deleteData() {
  this.bookingService.deletebooking(this.checkList).subscribe({
    next: (response: any) => {
      this.alertservice.showSuccess("Deleted Successfully!!!", "success");
      console.log("response = " + response);
      this.getPendingBookings();
    },
    error: (err: any) => {
      this.alertservice.showError("Failed to delete!!!", "error");
      console.log("error message" + err);
    },
    complete: () => {
      this.getPendingBookings();
    },
  });
}

  export() {
    console.log("export function");
    // this.bookingService.download().subscribe();
    // window.open("http://localhost:9091/bookings/download/3");
    this.http.get('http://localhost:9091/bookings/download/2/1', { responseType: 'blob' })
      .subscribe(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'cancelled-list.csv';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
      });
  }
  
}

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
  userId:any;
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
    this.userId=localStorage.getItem("userid")
    this.bookingService.getPending(this.userId).subscribe({
      next: (response: any) => {
        this.items = response;
        console.log(this.items)
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
    } else {
      const index = this.checkList.indexOf(e.target.value);
      this.checkList.splice(index, 1);
    }
  }
 
//delete booking details of selected ids
deleteData() {
  this.bookingService.deletebooking(this.checkList).subscribe({
    next: (response: any) => {
      this.alertservice.showSuccess("Deleted Successfully!!!", "success");
      this.getPendingBookings();
    },
    error: (err: any) => {
      this.alertservice.showError("Failed to delete!!!", "error");
    },
    complete: () => {
      this.getPendingBookings();
    },
  });
}

  export() {
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

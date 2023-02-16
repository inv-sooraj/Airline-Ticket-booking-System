import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";

@Component({
  selector: "app-user-booking-list",
  templateUrl: "./user-booking-list.component.html",
  styleUrls: ["./user-booking-list.component.css"],
})
export class UserBookingListComponent {
  bookingListForm!: FormGroup;
  items: any[] = [];
  itemName: any;
  item: any;
  public searchData: any[] = [];
  searchText: any;
  closeResult = "";
  constructor(
    private formbuilder: FormBuilder,
    private alertservice: AlertService,
    private modalService: NgbModal,
    private apiService: ApiService
  ) {}
  ngOnInit(): void {
    this.getUserBookingList();
    this.bookingListForm = this.formbuilder.group({
      search: [""],
      company: [""],
      sel: this.formbuilder.array([]),
    });
  }
  Search() {
    if (this.itemName == "") {
      this.ngOnInit();
    } else {
      this.searchData = this.searchData.filter((res) => {
        return res.itemName
          .toLocaleLowerCase()
          .match(this.itemName.toLocaleLowerCase());
      });
    }
  }
  

  /**To fetch the user data of the spacific id from db */

  getUserBookingList() {
    this.apiService.getUserBookingList().subscribe({
      next: (response: any) => {
        this.items = response;
        console.log("user bookings response", this.items);
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to load user booking", "Error");
      },
      complete: () => {},
    });
  }
  open(content: any) {
    this.modalService.open(content, { ariaLabelledBy: "modal-basic-title" });
  }

  getBookingDetails(id: any) {
    this.apiService.bookingDetailsById(id).subscribe({
      next: (response: any) => {
        this.item = response;
        console.log("user booking details response", this.item);
        console.log("departure", response.departure);
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to load user booking", "Error");
      },
      complete: () => {},
    });
  }
  changeStaus() {
    this.apiService.cancelBooking(this.item.bookingId).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess(
          "Reservation cancelled sussessfully!!!",
          "Success"
        );
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to cancel reservation", "Error");
      },
      complete: () => {this.getUserBookingList();},
    });
  }
}

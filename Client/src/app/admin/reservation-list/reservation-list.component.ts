import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { AlertService } from 'src/app/alert.service';
import { BookingServiceService } from '../services/booking-service.service';
@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  items: any[] = [];
site:any[]=[];
  bookingListForm!: FormGroup;
  role: any;
  searchText:any;
  userid: any;
  itemName: any;
  public data: any;
  public searchData: any[] = [];
  constructor(private formbuilder: FormBuilder,private alertservice: AlertService,private bookingService:BookingServiceService) { 
    this.bookingListForm = this.formbuilder.group({
      search: [''],
      sel: this.formbuilder.array([])
      // sel: this.formbuilder.array([])
    });
  }
  ngOnInit(): void {
    this.getBookings();
  }
approve(id:any,status:any){
  this.bookingService.changeStatus(id,status).subscribe
    ({
      
      next: (response: any) => {
      
        this.alertservice.showSuccess("Status changed Successfully!!!", "success")
        
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to Change Status!!!", "error")
        console.log("error message"+err)
      },
      complete: () => { this.getBookings();}
    });
}

  deleteData() {
    this.bookingService.deletebooking(this.site).subscribe
      ({
      
        next: (response: any) => {
          this.alertservice.showSuccess("Deleted Successfully!!!", "success")
          console.log("response = "+response)
          this.getBookings() 
        },
        error: (err: any) => {
          this.alertservice.showError("Failed to delete!!!", "error")
          console.log("error message"+err)
        },
        complete: () => { 
          this.getBookings() 
        }
      });
  }
  export(){
    console.log("export function");
    // this.bookingService.download().subscribe();
    window.open("http://localhost:9091/bookings/download");
  }
  onCheckboxChange(e: any) {
    // const site: FormArray = this.bookingListForm.get('sel') as FormArray;

    if (e.target.checked) {
      this.site.push(e.target.value);
      console.log("ids are : "+this.site);
}
  }
  getBookings() {
    // if (this.role == '1') {
      this.bookingService.getBooking().subscribe({
        next: (response: any) => {
          this.items = response;
          console.log(this.items);
        },
        error: (err: any) => {
          this.alertservice.showError("Failed to load airplane data", "Error")
        },
        complete: () => { }
      });
      

    // }
    // else if (this.role == '2') {
    //   this.apiservice.getPlaneByCompany(this.userid).subscribe({
    //     next: (response: any) => {
    //       this.items = response;
    //       console.log("airplane by  company", this.items);
    //     },
    //     error: (err: any) => {
    //       this.alertservice.showError("Failed to load airplane data", "Error")

    //     },
    //     complete: () => { }
    //   });
    // }
    // else {
    //   this.alertservice.showError("Access Denied", "Error")
    // }
}
Search() {
  console.log(this.itemName)
  if (this.itemName == "") {
    this.ngOnInit();
  }
  else {
    this.searchData = this.searchData.filter(res => {
      return res.itemName.toLocaleLowerCase().match(this.itemName.toLocaleLowerCase());
    });
  }
}}

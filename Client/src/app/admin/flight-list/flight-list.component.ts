import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent implements OnInit {
  flightlist!:FormGroup
  searchText:any;
  parentSelector:any;
  flight:any;
  userId:any;
  company:any;
  constructor(private service:ServiceService) { }

  ngOnInit(): void {
    this.getFlight();
  }

  // value get fuction
  getFlight() {
    this.service.getFlightList(this.userId).subscribe((result: any) => {
      this.flight = result;
    })
  }

  changecompany(e: any) {
    this.flightlist?.get('company')?.setValue(e.target.value, {
      onlySelf: true
    });
    // this.onSubmit();
  }

  // onSubmit() {
  //   // this.status = true;
  //   this.service.getPlaneByCompany(this.flightlist.value.company).subscribe({
  //     next: (response: any) => {
  //       this.items = response;
  //     },
  //     error: (err: any) => { alert("Failed") },
  //     complete: () => { }
  //   });

  

  onChangeCompany($event:any){}
  editcompany(f:any){}
}

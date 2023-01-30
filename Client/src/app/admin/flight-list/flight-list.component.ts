import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent implements OnInit {
  flightlist: FormGroup = new FormGroup(
    {
      userId: new FormControl('', Validators.required),

    })

  searchText: any;
  parentSelector: any;
  flight: any;
  userId: any;
  company: any;
  companyId: any;

  constructor(private service: ServiceService,private formbuilder:FormBuilder ) {}

  ngOnInit(): void {
    console.log(this.flightlist.controls['userId'].value);
    this.getCompany();
    this.getFlight();
  }
  // To takes company 
  getCompany() {
    this.service.getCompanyList().subscribe((result: any) => {
      this.company = result;
      console.log(result);

    })
  }
  // Id pass to api to load flights
  getFlight() {
    this.service.getFlightList(this.companyId).subscribe((result: any) => {
      this.flight = result;
    })
  }
  // To Load id
  changeCompany(event: any) {
    this.companyId = this.flightlist.controls['userId'].value;
    console.log(this.companyId);
    this.getFlight();
  }

  onChangeCompany(event: any) { }

  delete(){
    
  }
}

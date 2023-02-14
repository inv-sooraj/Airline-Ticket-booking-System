import { Component, OnInit } from '@angular/core';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
items:any
  constructor(private apiservice: ApiService,private alertservice: AlertService) { }

  ngOnInit(): void {
    this.getRandomFlight();
  }
getRandomFlight(){
  this.apiservice.getRandom().subscribe({
    next: (response: any) => {
      this.items = response;
      console.log("Random Flights are : ", this.items);
    },
    error: (err: any) => {
      this.alertservice.showError("Failed to load random Flights", "Error");
    },
    complete: () => {},
  });
}
}

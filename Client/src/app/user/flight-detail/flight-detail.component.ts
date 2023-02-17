import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/api.service';
@Component({
  selector: 'app-flight-detail',
  templateUrl: './flight-detail.component.html',
  styleUrls: ['./flight-detail.component.css']
})
export class FlightDetailComponent implements OnInit {
  items:any;
  constructor(private route: ActivatedRoute,private apiService:ApiService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const flightId = params['flightId'];
      this.apiService
      .getFlightDetail(flightId)
      .subscribe({
        next: (response: any) => {
          this.items = response;
          console.log("Flight Detail = "+this.items.flightId)
        },
        error: (err: any) => {
          alert("Failed");
        },
        complete: () => {},
      });
  }
     
     
    );
  }
}

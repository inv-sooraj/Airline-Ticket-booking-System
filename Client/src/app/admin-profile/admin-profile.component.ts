import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AlertService } from '../alert.service';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent implements OnInit {

  userid: any;
  items: any;
  constructor(
    private formbuilder: FormBuilder,
    private apiservice: ApiService,
    private alertservice: AlertService
  ) {
    this.userid = localStorage.getItem("userid");
  }
  ngOnInit(): void {
    this.getUserDetails()
  }
  getUserDetails()
  {
    this.apiservice.getUserById(this.userid).subscribe({
      next: (response: any) => {
        this.items = response;
        console.log("user details response", this.items);
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to load user details", "Error");
      },
      complete: () => {},
    });
  }
}


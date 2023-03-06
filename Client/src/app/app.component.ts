import { Component, OnInit } from '@angular/core';
import { Role } from './role';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  role:any
  ngOnInit(): void {
    this.role=localStorage.getItem("Role");
    
  }
  title = 'airline-ui';
}

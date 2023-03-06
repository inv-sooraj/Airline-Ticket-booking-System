import { ChangeDetectorRef, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  role:any
  constructor(private cdRef: ChangeDetectorRef){
    
  }
  ngOnInit(): void {
    this.role=localStorage.getItem("Role");
    
  }
  title = 'airline-ui';
}

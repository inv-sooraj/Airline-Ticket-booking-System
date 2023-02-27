import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-company-nav',
  templateUrl: './company-nav.component.html',
  styleUrls: ['./company-nav.component.css']
})
export class CompanyNavComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  logOut(){
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}

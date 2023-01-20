import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {
  searchText:any;
  parentSelector:any;
  flight:any;
  constructor() { }

  ngOnInit(): void {
  }

  onChangeFlight($event:any){}
}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cancel-res-list',
  templateUrl: './cancel-res-list.component.html',
  styleUrls: ['./cancel-res-list.component.css']
})
export class CancelResListComponent implements OnInit {
  cancel: any;
  searchText: any;
  parentSelector: any;
  constructor() { }

  ngOnInit(): void {
  }
  CancelReservationList(event: any) { }
  delete() { }
  dwn() { }
}

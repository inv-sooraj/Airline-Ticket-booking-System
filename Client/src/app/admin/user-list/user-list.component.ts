import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  user: any;
  searchText: any;
  parentSelector: any;
  constructor() { }

  ngOnInit(): void {
  }
  onChangeUser(event: any) { }
  editUser(n: any) { }
  delete() { }
  dwn() { }
}

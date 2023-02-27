import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder } from "@angular/forms";
import { AlertService } from "../alert.service";
import { ApiService } from "../api.service";

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html",
  styleUrls: ["./user-list.component.css"],
})
export class UserListComponent implements OnInit {
  UserListForm!: FormGroup;
  searchText: any;
  itemName: any;
  website: any[] = [];
  items: any[] = [];
  Company: any;
  public data: any;
  public searchData: any[] = [];
  parentSelector: boolean = false;
  status: any = false;
  role: any;
  userid: any;
  constructor(
    private formbuilder: FormBuilder,
    private apiservice: ApiService,
    private alertservice: AlertService
  ) {
    this.UserListForm = this.formbuilder.group({
      search: [""],
      company: [""],
      sel: this.formbuilder.array([]),
    });
  }

  /**For storing the id of selected users in array */

  onCheckboxChange(e: any) {
    if (e.target.checked) {
      this.website.push(e.target.value);
      console.log("ids are : " + this.website);
    } else {
      const index = this.website.indexOf(e.target.value);
      this.website.splice(index, 1);
      console.log("Array after unchecked", this.website);
    }
  }

  ngOnInit(): void {
    this.getUserDetails();
  }

  /**To get all airplane details(only for  admin) */

  getUserDetails() {
    this.apiservice.getAllUsers().subscribe({
      next: (response: any) => {
        this.items = response;
        console.log("All user details", this.items);
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to load user data", "Error");
      },
      complete: () => {},
    });
  }
}

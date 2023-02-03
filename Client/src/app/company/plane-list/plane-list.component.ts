import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-plane-list',
  templateUrl: './plane-list.component.html',
  styleUrls: ['./plane-list.component.css']
})
export class PlaneListComponent implements OnInit {

  planeListForm!: FormGroup;
  searchText: any;
  itemName: any;
  website:any[]=[];
  items: any[] = [];
  Company: any;
  public data: any;
  public searchData: any[] = [];
  parentSelector: boolean = false;
  status: any = false;
  role: any;
  userid: any;
  constructor(private formbuilder: FormBuilder, private apiservice: ApiService, private alertservice: AlertService) {
    this.planeListForm = this.formbuilder.group({
      search: [''],
      company: [''],
      sel: this.formbuilder.array([])
    });
    this.role = localStorage.getItem('Role');
    this.userid = localStorage.getItem('userid');
    if (this.role == '1') {
      this.status = true;
    }
  }

  /**For storing the id of selected airplanes in formarray */

  onCheckboxChange(e: any) {
    // const website: FormArray = this.planeListForm.get('sel') as FormArray;

    
    if (e.target.checked) {
      this.website.push(e.target.value);
      console.log("ids are : "+this.website);
}
  }

  /**For selecting company from dropdown list(for admin only) */

  changeCompany(e: any) {
    this.planeListForm?.get('company')?.setValue(e.target.value, {
      onlySelf: true
    });
    this.onSubmit();
  }

  /**For fetching airplane details based on  a spacific company id */

  onSubmit() {
    this.apiservice.getPlaneByCompany(this.planeListForm.value.company).subscribe({
      next: (response: any) => {
        this.items = response;
      },
      error: (err: any) => { alert("Failed") },
      complete: () => { }
    });

  }

  /**For searching an airplane */

  Search() {
    if (this.itemName == "") {
      this.ngOnInit();
    }
    else {
      this.searchData = this.searchData.filter(res => {
        return res.itemName.toLocaleLowerCase().match(this.itemName.toLocaleLowerCase());
      });
    }
  }

  ngOnInit(): void {
    this.getPlane();
    this.getCompanyName();
  }

  /**To get company name from the user table */

  getCompanyName() {
    this.apiservice.getCompany().subscribe({
      next: (response: any) => {
        this.Company = response;
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch company details", "error")
      },
      complete: () => { }
    });
  }

  /**To get all airplane details(only for  admin) */

  getPlane() {
    if (this.role == '1') {
      this.apiservice.getAirPlane().subscribe({
        next: (response: any) => {
          this.items = response;
          console.log(this.items);
        },
        error: (err: any) => {
          this.alertservice.showError("Failed to load airplane data", "Error")
        },
        complete: () => { }
      });
    }
    else if (this.role == '2') {
      this.apiservice.getPlaneByCompany(this.userid).subscribe({
        next: (response: any) => {
          this.items = response;
          console.log("airplane by  company", this.items);
        },
        error: (err: any) => {
          this.alertservice.showError("Failed to load airplane data", "Error")

        },
        complete: () => { }
      });
    }
    else {
      this.alertservice.showError("Access Denied", "Error")
    }

  }

  /**To delete the selected airplanes */

  deleteData() {
    this.apiservice.deletePlane(this.website).subscribe
      ({
        next: (response: any) => {
          this.alertservice.showSuccess("Deleted Successfully!!!", "success")
        
        },
        error: (err: any) => {
          this.alertservice.showError("Failed to delete!!!", "error")
         
        },
        complete: () => { 
          this.getPlane();
        }
      });
  }
}




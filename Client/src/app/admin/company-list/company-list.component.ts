import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {
  Company:any[]=[];
  companyIds: any[] = [];
  searchText: any;
  companyListForm !: FormGroup;
  constructor(private apiservice:ApiService,private alertservice:AlertService,private formbuilder:FormBuilder ){

    this.companyListForm = this.formbuilder.group({
      search: [""]
    });
  }
  ngOnInit(): void {
    this.getCompany();
  }
  getCompany(){

    this.apiservice.getCompany().subscribe({
      next: (response: any) => {
        this.Company = response;
        console.log("Company details",this.Company);
        
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch company details", "error");
      },
      complete: () => {},
    });
  }
  onCheckboxChange(e: any) {

    if (e.target.checked) {
      this.companyIds.push(e.target.value);
      console.log("Arrayyyy " + this.companyIds);
    } else {
      const index = this.companyIds.indexOf(e.target.value);
      this.companyIds.splice(index, 1);
      console.log("Array after unchecked", this.companyIds);
    }
  }
  deleteData() {
    this.apiservice.deleteCompany(this.companyIds).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess("Deleted Successfully!!!", "success");
      },
      error: (err: any) => {
        this.alertservice.showError("Failed to delete!!!", "error");
      },
      complete: () => {
        this. getCompany();
      },
    });
  }

}

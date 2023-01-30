import { DatePipe } from '@angular/common';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { pipe } from 'rxjs';
import { ServiceService } from 'src/app/service.service';
import { saveAs } from 'file-saver'
import { Router } from '@angular/router';
@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {
  onChangeCompanys($event: Event) {
    throw new Error('Method not implemented.');
  }
  searchText: any;
  parentSelector: any;
  company: any;
  filename: any;
  constructor(private service: ServiceService, private datepipe: DatePipe, public router: Router,) { }

  ngOnInit(): void {
    this.getCompany();
  }
  // fetch values
  getCompany() {
    this.service.getCompanyList().subscribe((result: any) => {
      this.company = result;
    })
  }
  //export csv
  dwn() {
    let currentDateTime = this.datepipe.transform((new Date), 'MM/dd/yyyy');
    this.filename = "DataExport_" + currentDateTime;
    this.service.exportCompany().subscribe((blob: any) => saveAs(blob, this.filename))
  }
  newArray: any = [];
  onChangeCompany(ev: any, data: any) {


    if (ev.target.checked) {
      // Pushing the object into array
      this.newArray.push(data.userId);

    } else {
      let el = this.newArray.filter((itm: any) => data.userId !== data.userId);
    
      if (el)
        this.newArray.splice(this.newArray.indexOf(el), 1);
      // console.log(this.newArray)
    }

    //Duplicates the obj if we uncheck it
    //How to remove the value from array if we uncheck it
    console.log(this.newArray);
  }


  // Company edit ,move to edit page
  editcompany(n: any) {
    this.router.navigate(['companyedit/', n.userId])
  }
  delete() {
    this.service.delete(this.newArray).subscribe(res => {
      // if(res==null)
      // alert("deleted")
      this.getCompany();
    })
    this.router.navigate(['companylist']);
  }
}

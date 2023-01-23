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
  searchText:any;
  parentSelector:any;
  company:any;
  filename: any;
  constructor(private service:ServiceService,private datepipe:DatePipe,public router:Router) { }

  ngOnInit(): void {
    this.getCompany();
  }

  // fetch values

  getCompany(){
    this.service.getCompanyList().subscribe((result:any)=>{
     this.company=result;  
    })
  }

  //export csv

  dwn() {
    
    let currentDateTime =this.datepipe.transform((new Date), 'MM/dd/yyyy');
    this.filename="DataExport_"+currentDateTime;
    this.service.exportCompany().subscribe((blob:any)=>saveAs(blob,this.filename))
  }

  onChangeCompany($event:any){}

  // Company edit ,move to edit page
  editcar(n:any){
    this.router.navigate(['companyedit/',n.userId])
    }
}

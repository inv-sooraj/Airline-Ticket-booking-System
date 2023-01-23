import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-plane-list',
  templateUrl: './plane-list.component.html',
  styleUrls: ['./plane-list.component.css']
})
export class PlaneListComponent implements OnInit {

  planeListForm!: FormGroup;
  Company: any = ['INDIGO', 'AIR INDIA', 'BDHG', 'ASDG'];
  title = 'toolsets';
  searchText: any;
  itemName: any;
  items: any
  public dataarray: any[] = [];
  public searchData: any[] = [];
  parentSelector: boolean = false;
  constructor(private formbuilder: FormBuilder, private apiservice: ApiService) { }
  onChange($event: any) {
    const id = $event.target.value;
    const isChecked = $event.target.checked;

    this.items = this.items.map((d: any) => {
      if (d.airplaneId == id) {
        d.select = isChecked;
        this.parentSelector = false;
        this.dataarray.push(id);
        console.log(this.dataarray);
        return d;
      }
      if (id == -1) {
        d.select = this.parentSelector;
        return d;
      }
      // console.log(id);
      return d;
    });
  }

  // changeCompany(e:any) {
  //   console.log(e.value)
  //   this.Company.setValue(e.target.value, {
  //     onlySelf: true
  //   })
  // }
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
  }
  getPlane() {
    this.apiservice.getAirPlane().subscribe({
      next: (response: any) => {
        this.items = response;
        console.log(this.items);
      },
      error: (err: any) => { alert("Failed") },
      complete: () => { }
    });
  }
  deleteData() {
    this.apiservice.deletePlane(this.dataarray).subscribe({
      next: (response: any) => {
        alert("Deleted")
        location.reload();
      },
      error: (err: any) => { alert("Failed to delete") },
      complete: () => { }
    });
  }
}


import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-plane-list',
  templateUrl: './plane-list.component.html',
  styleUrls: ['./plane-list.component.css']
})
export class PlaneListComponent implements OnInit {
  ngOnInit(): void {
    
  }
  planeListForm!:FormGroup;
  Company: any = ['INDIGO', 'AIR INDIA','BDHG','ASDG'];
  title = 'toolsets';
  searchText:any;
  itemName:any;
  public dataarray: any[] = [];
  parentSelector: boolean = false;
  food = [
    { id: 1, select: false, name: 'FBGFHB', modelno: 'fgsfg', seats: '12' },
    { id: 2, select: false, name: 'dumpUOUIOling', modelno: 'fgsfg', seats: '12' },
    {id: 3, select: false, name: 'JHKJH', modelno: 'fgsfg', seats: '12' }
  ];

  onChangeFood($event:any) {
    const id = $event.target.value;
    const isChecked = $event.target.checked;

    this.food = this.food.map((d) => {
      if (d.id == id) {
        d.select = isChecked;
        this.parentSelector = false;
        return d;
      }
      if (id == -1) {
        d.select = this.parentSelector;
        return d;
      }
      return d;
    });
    console.log(this.food);
  }

  changeCompany(e:any) {
    console.log(e.value)
    this.Company.setValue(e.target.value, {
      onlySelf: true
    })
  }
  Search(){
    if(this.itemName ==""){
      this.ngOnInit();
    }
    else{
      this.dataarray=this.dataarray.filter(res=>{
        return res.itemName.toLocaleLowerCase().match(this.itemName.toLocaleLowerCase());
      });
    }
  }
}

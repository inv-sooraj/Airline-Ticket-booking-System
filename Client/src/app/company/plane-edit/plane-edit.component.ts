import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-plane-edit',
  templateUrl: './plane-edit.component.html',
  styleUrls: ['./plane-edit.component.css']
})
export class PlaneEditComponent implements OnInit {
  airplaneId = -1;
  public dataarray: any;
  planeEditForm!:FormGroup
   data : any
  constructor(private formbuilder:FormBuilder,private apiservice:ApiService,private router:Router,private route :ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      this.airplaneId = params['airplaneId'];});
      console.log(this.airplaneId);
      
      this.planeEditForm=this.formbuilder.group({
        airplaneName:['',[Validators.required,Validators.pattern("^[a-zA-Z0-9]*$"),Validators.minLength(5),Validators.maxLength(30)]],
        modelNo:['',[Validators.required,Validators.pattern("^[a-zA-Z0-9]*$"),Validators.minLength(5),Validators.maxLength(30)]],
        totalSeats:['',[Validators.required,Validators.pattern("^[0-9]*$"),Validators.maxLength(9),Validators.maxLength(9)]],
           });
    
      this.apiservice.getAirPlaneById(this.airplaneId).subscribe((response: any) => {
      this.data=response;
    
      });
  }
  onEditSubmit() {
    this.apiservice.sendUpdatePlane(this.planeEditForm.value,this.airplaneId).subscribe((confirmation: any) => {console.log(confirmation);
      if (confirmation.alert === "Sucess") {
      location.reload();
      }
      alert("Value updated")
      this.router.navigate(['/plane-list'])
      });
    }
    cancel()
  {
    this.router.navigate(['/plane-list'])

  }

}

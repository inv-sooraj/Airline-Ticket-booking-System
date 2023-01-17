import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-airplane-reg',
  templateUrl: './airplane-reg.component.html',
  styleUrls: ['./airplane-reg.component.css']
})
export class AirplaneRegComponent implements OnInit {

  planeRegForm!:FormGroup;
  status:any=false;
  constructor(private formbuilder:FormBuilder,private router:Router,private apiservice:ApiService) { 
    this.planeRegForm=this.formbuilder.group({
      name:['',[Validators.required,Validators.pattern("^[a-zA-Z0-9]*$")]],
      modelno:['',[Validators.required,Validators.pattern("^[a-zA-Z0-9]*$")]],
      seats:['',[Validators.required,Validators.pattern("^[0-9]*$")]],
        });
  }

  ngOnInit(): void {
  }
  addPlane()
  {
    if(this.planeRegForm.valid){
      this.apiservice.createPlane(this.planeRegForm.value).subscribe({
        next: (result: any) => {
        alert('Created successfully')
      //  this.router.navigate(['/login'])   
    },
    error: (err: any) => {
      alert(err.name);
      console.log(err);
    }  
    });
    }
    else{
        this.status=true;
    }
  }
  cancel()
  {
    this.planeRegForm.reset();
  }
  }



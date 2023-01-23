import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/api.service';

// import { ApiService } from '../api.service';

@Component({
  selector: 'app-airplane-reg',
  templateUrl: './airplane-reg.component.html',
  styleUrls: ['./airplane-reg.component.css']
})
export class AirplaneRegComponent implements OnInit {

  planeRegForm!:FormGroup;
  status:any=false;
  constructor(private formbuilder:FormBuilder,private router:Router,private apiservice:ApiService,@Inject(DOCUMENT) document: Document) { 
    this.planeRegForm=this.formbuilder.group({
      airplaneName:['',[Validators.required,Validators.pattern("^[a-zA-Z0-9]*$"),Validators.minLength(5),Validators.maxLength(30)]],
      modelNo:['',[Validators.required,Validators.pattern("^[a-zA-Z0-9]*$"),Validators.minLength(5),Validators.maxLength(30)]],
      totalSeats:['',[Validators.required,Validators.pattern("^[0-9]*$"),Validators.maxLength(9),Validators.maxLength(9)]],
        });
  }

  ngOnInit(): void {
  }
  addPlane()
  {
    if(this.planeRegForm.valid){
      this.apiservice.createPlane(this.planeRegForm.value).subscribe({
        next: (result: any) => {
          this.planeRegForm.reset();  
          
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



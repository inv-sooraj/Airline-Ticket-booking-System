import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
  userEditForm!:FormGroup
id:any;
data : any;
  constructor(private formbuilder:FormBuilder,private apiservice:ApiService,private router:Router,private route :ActivatedRoute,private alertservice:AlertService) { }

  ngOnInit(): void {
        
    this.userEditForm=this.formbuilder.group({
      address:['',[Validators.required]],
      dob:['',[Validators.required]],
      fullName:['',[Validators.required]],
      city:['',[Validators.required]],
      country: ['',[Validators.required]],
      email: ['',[Validators.required]],
      passportNumber:['',[Validators.required]],
      ​phone:['',[Validators.required]],
        });
    this.getData();
  }
  getData(){
    this.apiservice.getUserById(1).subscribe({
      next: (response: any) => {
        this.data = response;
        console.log("Editing details",response);
      },
      error: (err: any) => { 

        this.alertservice.showError("Failed to load user details","Error")
      },
      complete: () => { }
    });
  }

  onEditSubmit() {
    this.apiservice.updateUser(1,this.userEditForm.value).subscribe({
      next: (response: any) => {
        this.data = response;
        console.log("Editing details",response);
      },
      error: (err: any) => { 

        this.alertservice.showError("Failed to load user details","Error")
      },
      complete: () => { }
    });
    }

    deleteAccount() {
      this.apiservice.deleteUser(1).subscribe({
        next: (response: any) => {
          this.data = response;
       this.alertservice.showSuccess("Deletion Success","success")
        },
        error: (err: any) => { 
  
          this.alertservice.showError("Failed to delete user","Error")
        },
        complete: () => { }
      });
      }
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CompanyRegistrationComponent } from './admin-home/company-registration/company-registration.component';
import { AirplaneRegComponent } from './airplane-reg/airplane-reg.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { FlightRegComponent } from './flight-reg/flight-reg.component';
import { LoginComponent } from './login/login.component';
import { PlaneListComponent } from './plane-list/plane-list.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
 {path:'',component:AdminHomeComponent},
  {path:'companyreg',component:CompanyRegistrationComponent}
  {path:'signup',component:SignupComponent},
  {path:'login',component:LoginComponent},
  {path:'plane-reg',component:AirplaneRegComponent},
  {path:'flight-reg',component:FlightRegComponent},
  {path:'plane-list',component:PlaneListComponent},
  {path:'change-pass',component:ChangePasswordComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin/admin.component';
import { CompanyEditComponent } from './admin/company-edit/company-edit.component';
import { CompanyListComponent } from './admin/company-list/company-list.component';
import { CompanyRegistrationComponent } from './admin/company-registration/company-registration.component';
import { FlightListComponent } from './admin/flight-list/flight-list.component';
import { AirplaneRegComponent } from './company/airplane-reg/airplane-reg.component';
import { FlightRegComponent } from './company/flight-reg/flight-reg.component';
import { ChangePasswordComponent } from './shared/change-password/change-password.component';
import { LoginComponent } from './shared/login/login.component';
import { PlaneListComponent } from './shared/plane-list/plane-list.component';
import { HomeComponent } from './user/home/home.component';
import { SignupComponent } from './user/signup/signup.component';
import { UserListComponent } from './admin/user-list/user-list.component';
import { CancelResListComponent } from './admin/cancel-res-list/cancel-res-list.component';

const routes: Routes = [
  {path:'',component:AdminHomeComponent},
  {path:'companyreg',component:CompanyRegistrationComponent},
  {path:'companylist',component:CompanyListComponent},
  {path:'companyedit/:id',component:CompanyEditComponent},
  {path:'userlist',component:UserListComponent},
  {path:'cancelreservation',component:CancelResListComponent},
  {path:'signup',component:SignupComponent},
  {path:'flightlist',component:FlightListComponent},
  {path:'login',component:LoginComponent},
  {path:'plane-reg',component:AirplaneRegComponent},
  {path:'flight-reg',component:FlightRegComponent},
  {path:'plane-list',component:PlaneListComponent},
  {path:'change-pass',component:ChangePasswordComponent},
{path:'home',component:HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

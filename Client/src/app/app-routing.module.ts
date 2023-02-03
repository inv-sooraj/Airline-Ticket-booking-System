import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin/admin.component';
import { CompanyRegistrationComponent } from './admin/company-registration/company-registration.component';
import { ReservationListComponent } from './admin/reservation-list/reservation-list.component';
import { UserDetailComponent } from './admin/user-detail/user-detail.component';
import { AirplaneRegComponent } from './company/airplane-reg/airplane-reg.component';
import { FlightDetailsAdminComponent } from './company/flight-details-admin/flight-details-admin.component';
import { FlightListCompanyComponent } from './company/flight-list-company/flight-list-company.component';
import { FlightRegComponent } from './company/flight-reg/flight-reg.component';
import { ChangePasswordComponent } from './shared/change-password/change-password.component';
import { LoginComponent } from './shared/login/login.component';
import { PlaneEditComponent } from './company/plane-edit/plane-edit.component';
import { FlightDetailComponent } from './user/flight-detail/flight-detail.component';
import { HeaderComponent } from './user/header/header.component';
import { HomeComponent } from './user/home/home.component';
import { SignupComponent } from './user/signup/signup.component';
import { UserBookingListComponent } from './user/user-booking-list/user-booking-list.component';
import { PlaneListComponent } from './company/plane-list/plane-list.component';
import { UserProfileEditComponent } from './user/user-profile-edit/user-profile-edit.component';
import { AdminProfileComponent } from './admin-profile/admin-profile.component';
const routes: Routes = [
 {path:'',component:LoginComponent},
  {path:'companyreg',component:CompanyRegistrationComponent},
  {path:'signup',component:SignupComponent},
  {path:'login',component:LoginComponent},
  {path:'plane-reg',component:AirplaneRegComponent},
  {path:'flight-reg',component:FlightRegComponent},
  {path:'plane-list',component:PlaneListComponent},
  {path:'change-pass',component:ChangePasswordComponent},
  {path:'plane-edit/:airplaneId',component:PlaneEditComponent},
  {path:'home',component:HomeComponent},
  {path:'header',component:HeaderComponent},
  {path:'user-booking-list',component:UserBookingListComponent},
  {path:'flight-detail',component:FlightDetailComponent},
  {path:'reservation-list',component:ReservationListComponent},
  {path:'user-detail',component:UserDetailComponent},
  {path:'flight-details',component:FlightDetailsAdminComponent},
  {path:'flight-list',component:FlightListCompanyComponent},
  {path:'profile-edit',component:UserProfileEditComponent},
  {path:'profile',component:AdminProfileComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

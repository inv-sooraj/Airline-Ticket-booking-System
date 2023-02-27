import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AdminHomeComponent } from "./admin/admin.component";
import { CompanyRegistrationComponent } from "./admin/company-registration/company-registration.component";
import { ReservationListComponent } from "./admin/reservation-list/reservation-list.component";
import { UserDetailComponent } from "./admin/user-detail/user-detail.component";
import { AirplaneRegComponent } from "./company/airplane-reg/airplane-reg.component";
import { FlightDetailsAdminComponent } from "./company/flight-details-admin/flight-details-admin.component";
import { FlightListCompanyComponent } from "./company/flight-list-company/flight-list-company.component";
import { FlightRegComponent } from "./company/flight-reg/flight-reg.component";
import { ChangePasswordComponent } from "./shared/change-password/change-password.component";
import { LoginComponent } from "./shared/login/login.component";
import { PlaneEditComponent } from "./company/plane-edit/plane-edit.component";
import { FlightDetailComponent } from "./user/flight-detail/flight-detail.component";
import { HeaderComponent } from "./user/header/header.component";
import { HomeComponent } from "./user/home/home.component";
import { SignupComponent } from "./user/signup/signup.component";
import { UserBookingListComponent } from "./user/user-booking-list/user-booking-list.component";
import { PlaneListComponent } from "./company/plane-list/plane-list.component";
import { UserProfileEditComponent } from "./user/user-profile-edit/user-profile-edit.component";
import { AdminProfileComponent } from "./admin-profile/admin-profile.component";
import { CancelledReservationListComponent } from "./admin/cancelled-reservation-list/cancelled-reservation-list.component";
import { UserListComponent } from "./user-list/user-list.component";
import { ReservationDetailsComponent } from "./reservation-details/reservation-details.component";
import { UserFlightListComponent } from "./user/user-flight-list/user-flight-list.component";
import { FlightEditComponent } from "./company/flight-edit/flight-edit.component";
import { CompanyListComponent } from "./admin/company-list/company-list.component";
import { CompanyEditComponent } from "./company-edit/company-edit.component";
import { AdminDashboardComponent } from "./admin/admin-dashboard/admin-dashboard.component";
import { ForbiddenComponent } from "./shared/forbidden/forbidden/forbidden.component";
import { AuthGuard } from "./shared/auth-guard";
const routes: Routes = [
  
  


 
  




  { path: "",component:LoginComponent},
  { path: "companyreg", component: CompanyRegistrationComponent },
  { path: "signup", component: SignupComponent },
  { path: "login", component: LoginComponent },
  { path: "plane-reg", component: AirplaneRegComponent,canActivate:[AuthGuard],data:{allowedRoles:[2]} },
  { path: "flight-reg", component: FlightRegComponent,canActivate:[AuthGuard],data:{allowedRoles:[2]} },
  { path: "plane-list", component: PlaneListComponent ,canActivate:[AuthGuard],data:{allowedRoles:[1,2]}},
  { path: "change-pass", component: ChangePasswordComponent,canActivate:[AuthGuard],data:{allowedRoles:[1,2]} },
  { path: "plane-edit/:airplaneId", component: PlaneEditComponent ,canActivate:[AuthGuard],data:{allowedRoles:[1,2]}},
  { path: "home", component: HomeComponent,canActivate:[AuthGuard],data:{allowedRoles:[3]}}, // example protected route with allowed roles [2]
  { path: "header", component: HeaderComponent,canActivate:[AuthGuard],data:{allowedRoles:[3]}},
  { path: "user-booking-list", component: UserBookingListComponent },
  { path: "flight-detail/:flightId", component: FlightDetailComponent,canActivate:[AuthGuard],data:{allowedRoles:[3]} },
  { path: "reservation-list", component: ReservationListComponent,canActivate:[AuthGuard],data:{allowedRoles:[1]} },
  { path: "user-detail/:userId", component: UserDetailComponent ,canActivate:[AuthGuard],data:{allowedRoles:[1]}},
  { path: "flight-details", component: FlightDetailsAdminComponent ,canActivate:[AuthGuard],data:{allowedRoles:[1,2]}},
  { path: "flight-list", component: FlightListCompanyComponent ,canActivate:[AuthGuard],data:{allowedRoles:[1,2]}},
  { path: "profile-edit", component: UserProfileEditComponent ,canActivate:[AuthGuard],data:{allowedRoles:[3]}},
  { path: "profile", component: AdminProfileComponent,canActivate:[AuthGuard],data:{allowedRoles:[1,2]} },
  { path: "cancelled-list", component: CancelledReservationListComponent ,canActivate:[AuthGuard],data:{allowedRoles:[1,2]}},
  { path: "user-list", component: UserListComponent,canActivate:[AuthGuard],data:{allowedRoles:[1]} },
  { path: "reservation-details/:bookingId",component: ReservationDetailsComponent,canActivate:[AuthGuard],data:{allowedRoles:[1,2]}},
  { path: "flight-edit/:flightId", component: FlightEditComponent ,canActivate:[AuthGuard],data:{allowedRoles:[2]}},
  { path: "company-list", component: CompanyListComponent ,canActivate:[AuthGuard],data:{allowedRoles:[1]}},
  { path: "user-flight-list", component: UserFlightListComponent,canActivate:[AuthGuard],data:{allowedRoles:[3]} },
  { path: "company-edit/:userId", component: CompanyEditComponent,canActivate:[AuthGuard],data:{allowedRoles:[1]} },
  { path: "admin-dashboard", component: AdminDashboardComponent,canActivate:[AuthGuard],data:{allowedRoles:[1]} },
  { path: "forbidden", component: ForbiddenComponent },









];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

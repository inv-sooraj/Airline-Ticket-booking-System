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
const routes: Routes = [
  { path: "", component: LoginComponent },
  { path: "companyreg", component: CompanyRegistrationComponent },
  { path: "signup", component: SignupComponent },
  { path: "login", component: LoginComponent },
  { path: "plane-reg", component: AirplaneRegComponent },
  { path: "flight-reg", component: FlightRegComponent },
  { path: "plane-list", component: PlaneListComponent },
  { path: "change-pass", component: ChangePasswordComponent },
  { path: "plane-edit/:airplaneId", component: PlaneEditComponent },
  { path: "home", component: HomeComponent },
  { path: "header", component: HeaderComponent },
  { path: "user-booking-list", component: UserBookingListComponent },
  { path: "flight-detail/:flightId", component: FlightDetailComponent },
  { path: "reservation-list", component: ReservationListComponent },
  { path: "user-detail/:userId", component: UserDetailComponent },
  { path: "flight-details", component: FlightDetailsAdminComponent },
  { path: "flight-list", component: FlightListCompanyComponent },
  { path: "profile-edit", component: UserProfileEditComponent },
  { path: "profile", component: AdminProfileComponent },
  { path: "cancelled-list", component: CancelledReservationListComponent },
  { path: "user-list", component: UserListComponent },
  {
    path: "reservation-details/:bookingId",
    component: ReservationDetailsComponent,
  },

  { path: "flight-edit/:flightId", component: FlightEditComponent },
  { path: "company-list", component: CompanyListComponent },

  { path: "user-flight-list", component: UserFlightListComponent },
  { path: "company-edit/:userId", component: CompanyEditComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

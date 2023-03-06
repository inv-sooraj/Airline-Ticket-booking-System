
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { AdminHomeComponent } from "./admin/admin.component";
import { CompanyRegistrationComponent } from "./admin/company-registration/company-registration.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NavComponent } from "./company/nav/nav.component";
import { PlaneListComponent } from "./company/plane-list/plane-list.component";
import { Ng2SearchPipeModule } from "ng2-search-filter";
import { LoginComponent } from "./shared/login/login.component";
import { SignupComponent } from "./user/signup/signup.component";
import { AirplaneRegComponent } from "./company/airplane-reg/airplane-reg.component";
import { FlightRegComponent } from "./company/flight-reg/flight-reg.component";
import { ChangePasswordComponent } from "./shared/change-password/change-password.component";
import { PlaneEditComponent } from "./company/plane-edit/plane-edit.component";
import { ToastrModule } from "ngx-toastr";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HomeComponent } from "./user/home/home.component";
import { HeaderComponent } from "./user/header/header.component";
import { FooterComponent } from "./user/footer/footer.component";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { UserBookingListComponent } from "./user/user-booking-list/user-booking-list.component";
import { FlightDetailComponent } from "./user/flight-detail/flight-detail.component";
import { AdminHeaderComponent } from "./admin/admin-header/admin-header.component";
import { AdminFooterComponent } from "./admin/admin-footer/admin-footer.component";
import { ReservationListComponent } from "./admin/reservation-list/reservation-list.component";
import { AdminNavComponent } from "./admin/admin-nav/admin-nav.component";
import { UserDetailComponent } from "./admin/user-detail/user-detail.component";
import { FlightDetailsAdminComponent } from "./company/flight-details-admin/flight-details-admin.component";
import { FlightListCompanyComponent } from "./company/flight-list-company/flight-list-company.component";
import { CompanyNavComponent } from "./company/company-nav/company-nav.component";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { UserProfileEditComponent } from "./user/user-profile-edit/user-profile-edit.component";
import { AdminProfileComponent } from "./admin-profile/admin-profile.component";
import { CancelledReservationListComponent } from "./admin/cancelled-reservation-list/cancelled-reservation-list.component";
import { UserListComponent } from "./user-list/user-list.component";
import { ReservationDetailsComponent } from "./reservation-details/reservation-details.component";
import { UserFlightListComponent } from "./user/user-flight-list/user-flight-list.component";
import { FlightEditComponent } from "./company/flight-edit/flight-edit.component";
import { CommonModule, DatePipe } from "@angular/common";
import { CompanyListComponent } from "./admin/company-list/company-list.component";
import { CompanyEditComponent } from "./company-edit/company-edit.component";
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { MyInterceptor } from "./shared/my-interceptor";
import { ForbiddenComponent } from './shared/forbidden/forbidden/forbidden.component';
import { PassengerComponentComponent } from './user/passenger-component/passenger-component.component';
import { AdminComponentComponent } from './admin/admin-component/admin-component.component';
import { CompanyComponentComponent } from './company/company-component/company-component.component';
@NgModule({
  declarations: [
    AdminHomeComponent,
    CompanyRegistrationComponent,
    AppComponent,
    SignupComponent,
    LoginComponent,
    NavComponent,
    AirplaneRegComponent,
    FlightRegComponent,
    PlaneListComponent,
    ChangePasswordComponent,
    PlaneEditComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    UserBookingListComponent,
    FlightDetailComponent,
    AdminHeaderComponent,
    AdminFooterComponent,
    ReservationListComponent,
    AdminNavComponent,
    UserDetailComponent,
    FlightDetailsAdminComponent,
    FlightListCompanyComponent,
    CompanyNavComponent,
    UserProfileEditComponent,
    AdminProfileComponent,
    CancelledReservationListComponent,
    UserListComponent,
    ReservationDetailsComponent,
    UserFlightListComponent,
    FlightEditComponent,
    CompanyListComponent,
    CompanyEditComponent,
    AdminDashboardComponent,
    ForbiddenComponent,
    PassengerComponentComponent,
    AdminComponentComponent,
    CompanyComponentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    NgbModule,
    FontAwesomeModule,
  ],
  providers: [DatePipe,{ provide: HTTP_INTERCEPTORS, useClass:MyInterceptor, multi: true }],
  bootstrap: [AppComponent],
})
export class AppModule {}

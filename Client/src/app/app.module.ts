import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminHomeComponent } from './admin/admin.component';
import { CompanyRegistrationComponent } from './admin/company-registration/company-registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavComponent } from './company/nav/nav.component';
import { PlaneListComponent } from './company/plane-list/plane-list.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { LoginComponent } from './shared/login/login.component';
import { SignupComponent } from './user/signup/signup.component';
import { AirplaneRegComponent } from './company/airplane-reg/airplane-reg.component';
import { FlightRegComponent } from './company/flight-reg/flight-reg.component';
import { ChangePasswordComponent } from './shared/change-password/change-password.component';
<<<<<<< HEAD
import { PlaneEditComponent } from './company/plane-edit/plane-edit.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


=======
import { HomeComponent } from './user/home/home.component';
import { HeaderComponent } from './user/header/header.component';
import { FooterComponent } from './user/footer/footer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserBookingListComponent } from './user/user-booking-list/user-booking-list.component';
import { FlightDetailComponent } from './user/flight-detail/flight-detail.component';
import { AdminHeaderComponent } from './admin/admin-header/admin-header.component';
import { AdminFooterComponent } from './admin/admin-footer/admin-footer.component';
import { ReservationListComponent } from './admin/reservation-list/reservation-list.component';
import { AdminNavComponent } from './admin/admin-nav/admin-nav.component';
import { UserDetailComponent } from './admin/user-detail/user-detail.component';
import { FlightDetailsAdminComponent } from './company/flight-details-admin/flight-details-admin.component';
// import { HeaderCompanyComponent } from './company/header-company/header-company.component';
import { FlightListCompanyComponent } from './company/flight-list-company/flight-list-company.component';
import { CompanyNavComponent } from './company/company-nav/company-nav.component';
>>>>>>> origin/develop
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
<<<<<<< HEAD
    PlaneEditComponent
=======
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
    // HeaderCompanyComponent,
    FlightListCompanyComponent,
    CompanyNavComponent
>>>>>>> origin/develop
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2SearchPipeModule,
<<<<<<< HEAD
    ToastrModule.forRoot(),
    BrowserAnimationsModule
  
=======
    NgbModule
>>>>>>> origin/develop
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 
  
}

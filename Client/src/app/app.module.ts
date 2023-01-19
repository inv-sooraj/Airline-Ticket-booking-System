import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminHomeComponent } from './admin/admin.component';
import { CompanyRegistrationComponent } from './admin/company-registration/company-registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavComponent } from './company/nav/nav.component';
import { PlaneListComponent } from './shared/plane-list/plane-list.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { LoginComponent } from './shared/login/login.component';
import { SignupComponent } from './user/signup/signup.component';
import { AirplaneRegComponent } from './company/airplane-reg/airplane-reg.component';
import { FlightRegComponent } from './company/flight-reg/flight-reg.component';
import { ChangePasswordComponent } from './shared/change-password/change-password.component';
import { HomeComponent } from './user/home/home.component';
import { HeaderComponent } from './user/header/header.component';
import { FooterComponent } from './user/footer/footer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserBookingListComponent } from './user/user-booking-list/user-booking-list.component';
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
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    UserBookingListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    // NgbModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 
  
}

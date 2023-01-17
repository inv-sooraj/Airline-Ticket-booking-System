import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CompanyRegistrationComponent } from './admin-home/company-registration/company-registration.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavComponent } from './nav/nav.component';
import { AirplaneRegComponent } from './airplane-reg/airplane-reg.component';
import { FlightRegComponent } from './flight-reg/flight-reg.component';
import { PlaneListComponent } from './plane-list/plane-list.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
@NgModule({
  declarations: [
   AdminHomeComponent,
    CompanyRegistrationComponent
    AppComponent,
    SignupComponent,
    LoginComponent,
    NavComponent,
    AirplaneRegComponent,
    FlightRegComponent,
    PlaneListComponent,
    ChangePasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    // NgbModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2SearchPipeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

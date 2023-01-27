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
import { PlaneEditComponent } from './company/plane-edit/plane-edit.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


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
    PlaneEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

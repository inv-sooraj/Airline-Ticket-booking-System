import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CompanyRegistrationComponent } from './admin-home/company-registration/company-registration.component';

const routes: Routes = [
  {path:'',component:AdminHomeComponent},
  {path:'companyreg',component:CompanyRegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-company-nav",
  templateUrl: "./company-nav.component.html",
  styleUrls: ["./company-nav.component.css"],
})
export class CompanyNavComponent implements OnInit {
  userRole:any;
  status:any=false;
  adminStatus:any=true;
  constructor(private router: Router) {}

  ngOnInit(): void {
    this.userRole=localStorage.getItem("Role")
    if(this.userRole == 2){
      this.status=true;
    }
    
  }
  logOut() {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("Role");
    localStorage.clear();
    window.location.reload();
    this.router.navigate(["/login"]);
  }
}
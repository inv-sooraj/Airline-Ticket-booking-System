import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "./auth-service";
@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    constructor(private authService: AuthService, private router: Router) { }
  
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
      const allowedRoles = route.data["allowedRoles"] as Array<number>;
      const userRole = Number(localStorage.getItem("Role"));
      if (!userRole) {
        // If no role is found, redirect to the login page
        return this.router.parseUrl('/login');
      }
      if (!userRole || !allowedRoles.includes(userRole)) {
        return this.router.parseUrl('/forbidden');
      }
      
      return true;
    }
  }
import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "./auth-service";
@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    const allowedRoles = route.data['allowedRoles'] as Array<number>;
    const userRole = Number(localStorage.getItem('Role'));

    if (!userRole) {
      // If no role is found, redirect to the login page
      return this.router.parseUrl('/login');
    }

    if (!allowedRoles.includes(userRole)) {
      // If the user's role is not allowed, redirect to the forbidden page
      return this.router.parseUrl('/forbidden');
    }

    // if (userRole === 3) {
    //   // If the user's role is 3, redirect to the home page
    //   return this.router.parseUrl('/home');
    // }

    // If the user's role is 1 or 2, allow them to access the page
    return true;
  }
}
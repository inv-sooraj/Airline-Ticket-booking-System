import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor() { }
    getRole(): number | null {
      const roleStr = localStorage.getItem("Role");
      if (roleStr) {
        return parseInt(roleStr, 10);
      }
      return null;
    }
  }
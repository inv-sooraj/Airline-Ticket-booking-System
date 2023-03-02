import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
@Injectable()
export class MyInterceptor implements HttpInterceptor{
    
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authRequest = request.clone({
            setHeaders: {
              Authorization: `Airline ${localStorage.getItem("accessToken")}`
            }
          });
          console.log(localStorage.getItem("userid"));
          console.log("role = ",(localStorage.getItem("userid")));
          
          return next.handle(authRequest);
    }
}

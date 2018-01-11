import { Injectable } from "@angular/core";
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/empty';
import 'rxjs/add/operator/retry';
import 'rxjs/add/observable/throw';
import {Router, RouterStateSnapshot} from "@angular/router";

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
  constructor(private router: Router){}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request)
      .catch((error: any) => {
        if (error instanceof HttpErrorResponse) {
          if (error.status == 404){
            this.router.navigate(['error/404']);
          }
          if (error.status == 401){
            this.router.navigate(['login']);
          }
          if (error.status == 403){
            this.router.navigate(['error/403'])
          }
        }
        return Observable.throw(error);
      });
  }
}

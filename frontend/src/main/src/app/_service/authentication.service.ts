import { Injectable } from '@angular/core';
import { Constants } from "../_shared/constants";
import { HttpClient , HttpHeaders} from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { ApiUrls } from "../_shared/api-urls";
import { JwtHelper } from "angular2-jwt";
import { Subject } from "rxjs/Subject";
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {
  private username = new Subject<string>();
  username$ = this.username.asObservable();
  constructor(private http: HttpClient,
              private jwtHelper: JwtHelper) {
  }

  loginWithPassword(username: string, password: string) : Observable<boolean>{
    this.logout();
    let headers = this.createHeaders();
    const body = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&grant_type=password`;

    return this.http.post(ApiUrls.AUTH_TOKEN, body, {headers})
      .map((response: Response) => {
        let token = response['access_token'];
        if (token) {
          localStorage.setItem('username', username);
          localStorage.setItem('token', token);
          let refreshToken = response['refresh_token'];
          if (refreshToken) {
            localStorage.setItem('refresh_token', refreshToken);
          }
          this.username.next(username);
          return true;
        } else {
          return false;
        }
      });
  }
  private createHeaders(): HttpHeaders {
    let headers = new HttpHeaders();
    headers = headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers = headers.append('Authorization', 'Basic ' + btoa(Constants.OAUTH2_CLIENT_ID + ':' + Constants.OAUTH2_SECRET));
    return headers;
  }
  logout(): void {
    localStorage.removeItem('username');
    localStorage.removeItem('token');
    localStorage.removeItem('refresh_token');
    this.username.next(null);
  }
  static getToken(): string {
    return localStorage.getItem('token');
  }
  static getUsername(): string {
    return localStorage.getItem('username');
  }
  isAuthenticated(): boolean {
    let token = AuthenticationService.getToken();
    return (token && !this.jwtHelper.isTokenExpired(token));
   }
   isAdmin(): boolean {
     let token = AuthenticationService.getToken();
      if (!token){
        return false;
      }
     let decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.authorities.indexOf("ROLE_ADMIN") != -1
   }
}

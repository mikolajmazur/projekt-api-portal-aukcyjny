import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../_models/user";
import { Constants } from '../_shared/constants';
import {Observable} from "rxjs/Observable";
import {ApiUrls} from "../_shared/api-urls";

@Injectable()
export class UserService {
  private url = ApiUrls.USERS;
  constructor(private http: HttpClient) { }

  registerUser(user: any): boolean{
    this.http.post(this.url, user).subscribe(res => {
        console.log(res);
      },
      err => {
        console.log("Error rgistering user");
      });
    return true;
  }
  getUsers(): Observable<User[]> {
    const url = "api/v2/users";
    return this.http.get<User[]>(this.url);
  }
}

import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../_service/authentication.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  a: string;
  constructor(private http: HttpClient) { }

  ngOnInit() {
  }
  logout(){
    //AuthenticationService.logout();
  }
  seta(){
    this.a = "a";
  }
  reseta(){
    this.a = null;
  }
}

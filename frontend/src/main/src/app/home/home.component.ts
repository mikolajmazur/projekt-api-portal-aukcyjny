import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../_service/authentication.service";
import {JwtHelperService} from "@auth0/angular-jwt";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor() {}

  ngOnInit() {
  }
}


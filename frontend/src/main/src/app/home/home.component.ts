import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  testText: string;
  constructor(private http: HttpClient) { }

  ngOnInit() {
    const url = 'api/test';
    this.http.get(url, {responseType: 'text'}).subscribe(data => this.testText = data);
  }
}

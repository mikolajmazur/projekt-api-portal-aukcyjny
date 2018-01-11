import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../_service/user.service";
import {AuthenticationService} from "../_service/authentication.service";
import {SecurityUser} from "../_models/SecurityUser";
import {ActivatedRoute, Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private loginForm: FormGroup;
  private redirectUrl: string;
  private invalidCredentials: boolean;

  constructor(private authenticationService: AuthenticationService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private translator: TranslateService) {
    this.redirectUrl = this.activatedRoute.snapshot.queryParams['redirectTo'];
  }

  ngOnInit() {
    this.initForm();
  }
  initForm() {
    this.loginForm = new FormGroup({
      'username': new FormControl('', [Validators.required]),
      'password': new FormControl('', [Validators.required]),
    });
  }

  onSubmit(){
    let username: string = this.loginForm.get('username').value;
    let password: string = this.loginForm.get('password').value;
    this.authenticationService.loginWithPassword(username, password)
      .subscribe(result => {
        if (result === true){
          this.navigateAfterSuccess();
        } else {
          this.invalidCredentials = true;
        }
      }, error => {
        this.invalidCredentials = true;
      });
  }

  private navigateAfterSuccess() {
    if (this.redirectUrl) {
      this.router.navigateByUrl(this.redirectUrl);
    } else {
      this.router.navigate(['/']);
    }
  }
}

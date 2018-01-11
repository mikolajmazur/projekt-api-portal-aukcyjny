import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, NgForm, Validators, FormBuilder } from "@angular/forms";
import { UserService } from "../_service/user.service";
import { matchOtherValidator, passwordValidator, skypeNameValidator } from "@moebius/ng-validators";
import { Router } from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  private registrationForm: FormGroup;
  private error: String;
  constructor(private userService: UserService,
              private formBuilder: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.buildForm();
  }
  buildForm(){
    this.registrationForm = this.formBuilder.group({
        email: ['', [Validators.required, Validators.email]],
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        username: ['', [Validators.required, Validators.minLength(3)]],
        password: ['', [Validators.required, passwordValidator({ minLength: 6 })]],
        repeatPassword: ['', [Validators.required, matchOtherValidator('password')]],
        recaptcha: ['', [Validators.required]]
      }
    );
  }
  onSubmit(form: NgForm){
    let result = this.userService.registerUser(form.value);
    if (result){
      this.router.navigate(['/login']);
    } else {
      this.error = "Wystąpił błąd podczas rejestracji";
    }
  }
  resolved(captchaResponse: string) {
    console.log(`Resolved captcha with response ${captchaResponse}:`);
  }
}

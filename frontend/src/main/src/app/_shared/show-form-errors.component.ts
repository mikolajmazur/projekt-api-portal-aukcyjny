import { Component, Input } from '@angular/core';
import { AbstractControlDirective, AbstractControl } from '@angular/forms';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-show-form-errors',
  template: `
    <div *ngIf="shouldShowErrors()" class="alert alert-danger">
      {{ getError() | translate }}
    </div>
  `,
  styles: []
})
export class ShowFormErrorsComponent {
  private static readonly errorMessages = {
    'required': (params) => '##FIELD## can\'t be blank',
    'minlength': (params) => '##FIELD## should be minimum '+params.requiredLength+' characters',
    'maxlength': (params) => '##FIELD## should not be greater then '+params.requiredLength+' characters',
    'pattern': (params) => 'Should be a valid',
    'email': (params) => "Should be vaild email.",
    'matchOther': (params) => "Passwords don't mach",
    'passwordMinLengthRequired': (params) => "Password too short " + params.minLength,
  };
  @Input()
  private control: AbstractControlDirective | AbstractControl;
  constructor(private translator: TranslateService) {}
  shouldShowErrors(): boolean {
    return this.control &&
      this.control.errors &&
      (this.control.dirty || this.control.touched);
  }
  listOfErrors(): string[] {
    return Object.keys(this.control.errors)
      .map(field => this.getMessage(field, this.control.errors[field],this.control));
  }
  getError(): string {
    //console.log("show",this.control.errors);
    let errors = Object.keys(this.control.errors)
      .map(field => this.getMessage(field, this.control.errors[field],this.control));
    return errors[0];
  }
  private getMessage(type: string, params: any,control:any) {
    let fieldName = this.getControlName(control);
    this.translator.get(fieldName).subscribe(translatedName => { fieldName = translatedName });
    //console.log(params);
    let msg = ShowFormErrorsComponent.errorMessages[type](params);
    return msg.replace("##FIELD##",fieldName);
  }
  getControlName(c: AbstractControl): string | null {
    const formGroup = c.parent.controls;
    return Object.keys(formGroup).find(name => c === formGroup[name]) || null;
  }
}

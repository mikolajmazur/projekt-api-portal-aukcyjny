import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS} from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { JwtModule } from "@auth0/angular-jwt";

import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { AppRoutingModule } from './app-routing.module';
import { AuctionDetailComponent } from './auction-detail/auction-detail.component';
import { AddAuctionComponent } from './add-auction/add-auction.component';
import { HomeComponent } from './home/home.component';
import { AuctionService } from './_service/auction.service';
import { CategoryService} from "./_service/category.service";
import { AuctionListComponent } from './auction-list/auction-list.component';
import { RegistrationComponent } from './registration/registration.component';
import { ShowFormErrorsComponent } from './_shared/show-form-errors.component';
import { UserService } from "./_service/user.service";
import { LoginComponent } from './login/login.component';
import { AuthenticationService } from "./_service/authentication.service";
import { JwtHelper } from "angular2-jwt";
import { StandardUserGuard } from "./_authGuards/standard-user-guard.service";
import { RecaptchaModule } from "ng-recaptcha";
import { RecaptchaFormsModule } from "ng-recaptcha/forms";
import { HttpErrorInterceptor } from "./_interceptors/http-error-interceptor";
import { HttpErrorPageComponent } from './http-error-page/http-error-page.component';
import { UserControlPanelComponent } from './user-control-panel/user-control-panel.component';
import { AuctionsPageComponent } from './auctions-page/auctions-page.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';

export const createTranslateLoader = (http: HttpClient) => {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
};

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    AuctionDetailComponent,
    AddAuctionComponent,
    HomeComponent,
    AuctionListComponent,
    RegistrationComponent,
    ShowFormErrorsComponent,
    LoginComponent,
    HttpErrorPageComponent,
    UserControlPanelComponent,
    AuctionsPageComponent,
    AdminPanelComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,
        deps: [HttpClient]
      }
    }),
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return AuthenticationService.getToken();
        },
        whitelistedDomains: ['localhost:4200', 'localhost:8080']
      }
    }),
    RecaptchaModule.forRoot(),
    RecaptchaFormsModule
    // HttpClientInMemoryWebApiModule.forRoot(
    //   InMemoryAuctionService, {
    //     dataEncapsulation: false,
    //     passThruUnknownUrl: true
    //   }
    // )
  ],
  providers: [ AuctionService, CategoryService, UserService,
    AuthenticationService, JwtHelper, StandardUserGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    }
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }

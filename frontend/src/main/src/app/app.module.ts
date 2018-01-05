import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';

import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { AppRoutingModule } from './app-routing.module';
import { AuctionDetailComponent } from './auction-detail/auction-detail.component';
import { AddAuctionComponent } from './add-auction/add-auction.component';
import { AccountComponent } from './account/account.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { InMemoryAuctionService } from './shared/in-memory-auction.service';
import { AuctionService } from './shared/auction.service';
import { CategoriesListComponent } from './categories-list/categories-list.component';
import { CategoryService} from "./shared/category.service";
import { AuctionListComponent } from './auction-list/auction-list.component';
import { RegistrationComponent } from './registration/registration.component';
import { ShowFormErrorsComponent } from './show-form-errors.component';
import { UserServiceService } from "./shared/user-service.service";

export const createTranslateLoader = (http: HttpClient) => {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
};

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    AuctionDetailComponent,
    AddAuctionComponent,
    AccountComponent,
    HomeComponent,
    PageNotFoundComponent,
    CategoriesListComponent,
    AuctionListComponent,
    RegistrationComponent,
    ShowFormErrorsComponent
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
    // HttpClientInMemoryWebApiModule.forRoot(
    //   InMemoryAuctionService, {
    //     dataEncapsulation: false,
    //     passThruUnknownUrl: true
    //   }
    // )
  ],
  providers: [ AuctionService, CategoryService, UserServiceService ],
  bootstrap: [AppComponent]
})
export class AppModule { }

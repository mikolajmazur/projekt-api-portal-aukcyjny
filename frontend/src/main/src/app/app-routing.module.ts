import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AccountComponent } from './account/account.component';
import { AddAuctionComponent } from './add-auction/add-auction.component';
import { AuctionDetailComponent } from './auction-detail/auction-detail.component';
import { HomeComponent } from './home/home.component';
import { CategoriesListComponent } from "./categories-list/categories-list.component";
import { AuctionListComponent } from "./auction-list/auction-list.component";
import { RegistrationComponent } from "./registration/registration.component";
import { LoginComponent } from "./login/login.component";
import { StandardUserGuard } from "./_authGuards/standard-user-guard.service";
import { HttpErrorPageComponent } from "./http-error-page/http-error-page.component";

const routes: Routes = [
  { path: 'auction/:id', component: AuctionDetailComponent },
  { path: 'categories', component: CategoriesListComponent },
  { path: 'auction', component: AddAuctionComponent, canActivate:[StandardUserGuard] },
  { path: 'user', component: AccountComponent, canActivate: [StandardUserGuard] },
  { path: 'auctionList', component: AuctionListComponent },
  { path: 'category/:id', component: AuctionListComponent },
  { path: '', component: HomeComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'error/:code', component: HttpErrorPageComponent },
  { path: '**', redirectTo:'error/404'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AccountComponent } from './account/account.component';
import { AddAuctionComponent } from './add-auction/add-auction.component';
import { AuctionDetailComponent } from './auction-detail/auction-detail.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import {CategoriesListComponent} from "./categories-list/categories-list.component";
import {AuctionListComponent} from "./auction-list/auction-list.component";
import {RegistrationComponent} from "./registration/registration.component";

const routes: Routes = [
  { path: 'auction/:id', component: AuctionDetailComponent },
  { path: 'categories', component: CategoriesListComponent },
  { path: 'addAuction', component: AddAuctionComponent },
  { path: 'user', component: AccountComponent },
  { path: 'auctionList', component: AuctionListComponent },
  { path: 'category/:id', component: AuctionListComponent },
  { path: '', component: HomeComponent },
  { path: 'register', component: RegistrationComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

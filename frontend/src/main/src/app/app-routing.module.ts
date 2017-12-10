import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AccountComponent } from './account/account.component';
import { AddAuctionComponent } from './add-auction/add-auction.component';
import { AuctionDetailComponent } from './auction-detail/auction-detail.component';
import { CategoryComponent } from './category/category.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  { path: 'auction/:id', component: AuctionDetailComponent },
  { path: 'category/:id', component: CategoryComponent },
  { path: 'categories', component: CategoryComponent },
  { path: 'addAuction', component: AddAuctionComponent },
  { path: 'user', component: AccountComponent },
  { path: '', component: HomeComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

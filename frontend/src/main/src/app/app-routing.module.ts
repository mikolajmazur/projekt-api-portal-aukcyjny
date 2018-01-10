import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AddAuctionComponent } from './add-auction/add-auction.component';
import { AuctionDetailComponent } from './auction-detail/auction-detail.component';
import { HomeComponent } from './home/home.component';
import { AuctionListComponent } from "./auction-list/auction-list.component";
import { RegistrationComponent } from "./registration/registration.component";
import { LoginComponent } from "./login/login.component";
import { StandardUserGuard } from "./_authGuards/standard-user-guard.service";
import { HttpErrorPageComponent } from "./http-error-page/http-error-page.component";
import {UserControlPanelComponent} from "./user-control-panel/user-control-panel.component";
import {AdminPanelComponent} from "./admin-panel/admin-panel.component";

const routes: Routes = [
  { path: 'auction/:id', component: AuctionDetailComponent },
  { path: 'auction', component: AddAuctionComponent, canActivate:[StandardUserGuard] },
  { path: 'auction/:id/edit', component: AddAuctionComponent, canActivate: [StandardUserGuard] },
  { path: 'user', component: UserControlPanelComponent, canActivate: [StandardUserGuard] },
  { path: 'auctionList', component: AuctionListComponent },
  { path: 'category/:id', component: AuctionListComponent },
  { path: '', component: HomeComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'error/:code', component: HttpErrorPageComponent },
  { path: 'userAuctions', component: UserControlPanelComponent, canActivate: [StandardUserGuard] },
  { path: 'admin', component: AdminPanelComponent},
  { path: '**', redirectTo:'error/404'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

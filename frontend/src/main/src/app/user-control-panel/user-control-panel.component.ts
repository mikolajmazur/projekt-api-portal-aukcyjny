import { Component, OnInit } from '@angular/core';
import {AuctionService} from "../_service/auction.service";
import {AuthenticationService} from "../_service/authentication.service";
import {AuctionListItem} from "../_models/auctionListItem";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-control-panel',
  templateUrl: './user-control-panel.component.html',
  styleUrls: ['./user-control-panel.component.css']
})
export class UserControlPanelComponent implements OnInit {
  private username;
  private auctions: AuctionListItem[];
  constructor(private auctionService: AuctionService,
              private router: Router) { }

  ngOnInit() {
    this.username = AuthenticationService.getUsername();
    this.getAuctions();
  }
  getAuctions(){
    this.auctionService.getUserAuctions(this.username).subscribe(data =>
      this.auctions = data
    );
  }
  editClicked(id: number){
    this.router.navigate([`auction/${id}/edit`]);
  }
  deleteClicked(id: number){
    let auction: AuctionListItem = this.auctions.find(a => a.id === id);
    this.auctionService.deleteAuction(id).subscribe(response => {this.auctions.splice(this.auctions.indexOf(auction), 1)},
      err => {console.log(err)});
  }
}

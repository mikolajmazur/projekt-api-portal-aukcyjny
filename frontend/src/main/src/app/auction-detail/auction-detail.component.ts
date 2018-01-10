import { ActivatedRoute, ParamMap } from '@angular/router';
import { Component, Input, OnInit} from '@angular/core';
import 'rxjs/add/operator/switchMap';

import { Auction } from '../_models/auction';
import { AuctionService } from '../_service/auction.service';
import { Constants } from "../_shared/constants";
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-auction-detail',
  templateUrl: './auction-detail.component.html',
  styleUrls: ['./auction-detail.component.css']
})
export class AuctionDetailComponent implements OnInit {
  auction: Auction;
  auction$: Observable<Auction>;
  private error: boolean;

  constructor(
    private route: ActivatedRoute,
    private auctionService: AuctionService
  ) { }

  ngOnInit() {
    this.getAuction();
  }
  getAuction(): void {
    this.auction$ = this.route.paramMap
      .switchMap((params: ParamMap) =>
      this.auctionService.getAuction(+params.get('id')));
    this.auction$.subscribe(auction => this.auction = auction);
  }
  getDateTimeFormat(): string{
    return Constants.DATE_TIME_FORMAT;
  }
  makeBid(amount: string){
    console.log(amount);
    amount = amount.replace(',','');
    let num = +amount;
    console.log(num);
    this.error = false;
    this.auctionService.makeBidOnAuction(this.auction.id, num).subscribe(response => {
      this.auction.offers.push(response);
      this.auction.currentPrice = response.amount;
      this.auction.minimalBid = this.auction.currentPrice * 1.06;
    }, err => {
      this.error = true;
    });
  }
}

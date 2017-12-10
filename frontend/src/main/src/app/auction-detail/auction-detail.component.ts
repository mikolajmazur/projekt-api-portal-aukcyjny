import { ActivatedRoute, ParamMap } from '@angular/router';
import { Component, Input, OnInit} from '@angular/core';
import 'rxjs/add/operator/switchMap';

import { Auction } from '../auction';
import { AuctionService } from '../shared/auction.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-auction-detail',
  templateUrl: './auction-detail.component.html',
  styleUrls: ['./auction-detail.component.css']
})
export class AuctionDetailComponent implements OnInit {
  auction: Auction;
  auction$: Observable<Auction>;

  constructor(
    private route: ActivatedRoute,
    private auctionService: AuctionService
  ) { }

  ngOnInit() {
    this.getAuction();
  }
  getAuction(): void {
    // jezeli id nie ulega zmianie (nie ma przejsc do roznego id
    // w ramach tego samego komponentu)
    // const id = +this.route.snapshot.paramMap.get('id');
    // this.auctionService.getAuction(id)
    //   .subscribe(auction => this.auction = auction);
    this.auction$ = this.route.paramMap
      .switchMap((params: ParamMap) =>
      this.auctionService.getAuction(+params.get('id')));
    this.auction$.subscribe(auction => this.auction = auction);
  }
  getMinimalBid(): number{
    return this.auction.currentPrice * 1.06;
  }
}

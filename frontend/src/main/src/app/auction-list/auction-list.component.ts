import { Component, OnInit } from '@angular/core';
import { CategoryService } from "../_service/category.service";
import { AuctionListItem } from "../_models/auctionListItem";
import {ActivatedRoute, ParamMap} from "@angular/router";
import { Category } from "../_models/categoryModel";
import {Observable} from "rxjs/Observable";
import {AuctionsPage} from "../_models/auctionsPage";
import {AuctionService} from "../_service/auction.service";

@Component({
  selector: 'app-auction-list',
  templateUrl: './auction-list.component.html',
  styleUrls: ['./auction-list.component.css']
})
export class AuctionListComponent implements OnInit {
  auctions: AuctionListItem[];
  auctionsPage: AuctionsPage;
  category: Category;
  category$: Observable<Category>;
  pageSize: number = 20;
  pageNumber: number = 1;

  constructor(
    private categoryService: CategoryService,
    private auctionService: AuctionService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.getCategory();
    //this.getAuctions();
  }

  getAuctions() {
    const id = this.category.id;
    this.auctionService.getAuctionsFromCategory(id).subscribe(data => this.auctions = data);
  }
  getCategory() {
      this.category$ = this.route.paramMap
        .switchMap((params: ParamMap) => this.categoryService.getCategory(+params.get('id')));
      this.category$.subscribe(c =>
      {
        this.category = c;
        this.getAuctions()
      });
  }
}

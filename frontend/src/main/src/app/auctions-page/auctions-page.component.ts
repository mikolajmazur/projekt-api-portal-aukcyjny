import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AuctionListItem} from "../_models/auctionListItem";

@Component({
  selector: 'app-auctions-page',
  templateUrl: './auctions-page.component.html',
  styleUrls: ['./auctions-page.component.css']
})
export class AuctionsPageComponent implements OnInit {
  @Input()
  auctions: AuctionListItem[];
  @Input()
  shouldRenderEditAndDelete: boolean;
  @Output()
  onEditClicked: EventEmitter<number> = new EventEmitter<number>();
  @Output()
  onDeleteClicked: EventEmitter<number> = new EventEmitter<number>();
  constructor() { }

  ngOnInit() {}
  editClicked(id: number){
    this.onEditClicked.emit(id);
  }
  deleteClicked(id: number){
    this.onDeleteClicked.emit(id);
  }
}

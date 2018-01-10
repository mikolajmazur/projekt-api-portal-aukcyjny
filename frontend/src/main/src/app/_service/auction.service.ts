import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Auction } from '../_models/auction';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';
import {AuctionListItem} from "../_models/auctionListItem";
import {ApiUrls} from "../_shared/api-urls";
import {CreateAuctionForm} from "../_models/create-auction-form";
import {AuctionOffer} from "../_models/auctionOffer";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type' : 'application/json'})
};

class Offer{
  amount: number;
}

@Injectable()
export class AuctionService {
  constructor(private http: HttpClient) { }

  private auctionUrl = ApiUrls.AUCTIONS;

  getAuction(id: number): Observable<Auction> {
    const url = `${this.auctionUrl}/${id}`;
    return this.http.get<Auction>(url);
  }
  getAllAuctions(): Observable<AuctionListItem[]> {
    return this.http.get<AuctionListItem[]>(this.auctionUrl);
  }
  getUserAuctions(username: string): Observable<AuctionListItem[]> {
    const url = this.auctionUrl + `?username=${username}`;
    return this.http.get<AuctionListItem[]>(url);
  }
  getAuctionsFromCategory(categoryId: number): Observable<AuctionListItem[]>{
    const url = this.auctionUrl + `?category=${categoryId}`;
    return this.http.get<AuctionListItem[]>(url);
  }
  updateAuction(auction: CreateAuctionForm, id: number): Observable<any> {
    const url = `${this.auctionUrl}/${id}`;
    return this.http.put(url, auction, httpOptions);
  }
  createAuction(auction: CreateAuctionForm): Observable<Auction> {
    return this.http.post<Auction>(this.auctionUrl, auction, httpOptions);
  }
  deleteAuction(auction: Auction | number): Observable<Auction> {
    const id = typeof auction === 'number' ? auction : auction.id;
    const url = `${this.auctionUrl}/${id}`;

    return this.http.delete<Auction>(url, httpOptions);
  }
  makeBidOnAuction(auctionId: number, amount: number): Observable<any>{
    const url = `${this.auctionUrl}/${auctionId}/bid`;
    let amountobj = new Offer();
    amountobj.amount = amount;
    return this.http.post<AuctionOffer>(url, amountobj, httpOptions);
  }
}

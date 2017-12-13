import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Auction } from '../models/auction';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type' : 'application/json'})
};

@Injectable()
export class AuctionService {
  constructor(private http: HttpClient) { }

  private auctionUrl = 'api/v2/auctions';

  getAuction(id: number): Observable<Auction> {
    const url = `${this.auctionUrl}/${id}`;
    return this.http.get<Auction>(url)
      .pipe(
        catchError(this.handleError<Auction>(`getAuction id=${id}`))
      );
  }
  updateAuction(auction: Auction): Observable<any> {
    const url = `${this.auctionUrl}/${auction.id}`;
    return this.http.put(url, auction, httpOptions)
      .pipe(
        tap(_ => this.log(`updated auction id=${auction.id}`)),
        catchError(this.handleError<any>('updateAuction'))
      );
  }
  createAuction(auction: Auction): Observable<Auction> {
    return this.http.post<Auction>(this.auctionUrl, auction, httpOptions)
      .pipe(
        tap(_ => this.log(`added auction with id=${auction.id}`)),
        catchError(this.handleError<Auction>('addAuction'))
      );
  }
  deleteAuction(auction: Auction | number): Observable<Auction> {
    const id = typeof auction === 'number' ? auction : auction.id;
    const url = `${this.auctionUrl}/${id}`;

    return this.http.delete<Auction>(url, httpOptions)
      .pipe(
        tap(_ => this.log(`deleted auction id=${id}`)),
        catchError(this.handleError<Auction>('deleteAuction'))
      );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  // TODO: use actuall logger
  private log(message: string): void { }
}

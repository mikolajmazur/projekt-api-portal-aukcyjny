import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Category} from "../_models/categoryModel";
import {of} from "rxjs/observable/of";
import {catchError, tap} from "rxjs/operators";
import {AuctionListItem} from "../_models/auctionListItem";
import {AuctionsPage} from "../_models/auctionsPage";
import {ApiUrls} from "../_shared/api-urls";

@Injectable()
export class CategoryService {
  private url = ApiUrls.CATEGORIES;

  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url)
      .pipe(
        tap(_ => this.log('getCategories')),
        catchError(this.handleError<any>('getCategories'))
      );
  }

  getCategory(id: number): Observable<Category> {
    const requestUrl = `${this.url}/${id}`;
    return this.http.get<Category>(requestUrl)
      .pipe(
        tap(_ => this.log('getCategories')),
        catchError(this.handleError<any>('getCategories'))
      );
  }

  getAuctionsFromCategory(categoryId: number, pageNumber: number, pageSize: number): Observable<AuctionsPage> {
    const requestUrl = `${this.url}/${categoryId}/auctions?page=${pageNumber}&&pageSize=${pageSize}`;
    return this.http.get<AuctionsPage>(requestUrl)
      .pipe(
        tap(_ => this.log('getAuctionsFromCategory')),
        catchError(this.handleError<any>('getAuctionsFromCategory'))
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

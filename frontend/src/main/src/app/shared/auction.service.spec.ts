import {TestBed, inject, async} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { AuctionService } from './auction.service';
import {Auction} from '../models/auction';

describe('AuctionService', () => {
  let service: AuctionService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuctionService]
    });

    service = TestBed.get(AuctionService);
    httpMock = TestBed.get(HttpTestingController);
  });

  afterEach(inject([HttpTestingController], (httpMock: HttpTestingController) => {
    httpMock.verify();
  }));

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should GET correct auction', () => {
    service.getAuction(1).subscribe((data: any) => {
      expect(data['id']).toBe(1);
      expect(data['title']).toBe('Skarpety');
      expect(data['currentPrice']).toBe(10);
      expect(data['description']).toBe('opis');
      expect(data['endDateTime']).toBe('01.01.2018:13:10:15');
    });

    const req = httpMock.expectOne(`api/auctions/1`, 'get call');
    expect(req.request.method).toBe('GET');
    expect(req.request.body).toEqual(null);

    req.flush({
      id: 1,
      title: 'Skarpety',
      description: 'opis',
      currentPrice: 10,
      endDateTime: '01.01.2018:13:10:15'
    });
  });

  it('should update correct auction with PUT', () => {
    const auction: Auction = { id: 1, title: 'Skarpety', description: 'opis',
      currentPrice: 10, endDateTime: '01.01.2018:13:10:15'} as Auction;
    service.updateAuction(auction).subscribe((data) => {
      expect(data['id']).toBe(1);
      expect(data['title']).toBe('Skarpety');
      expect(data['currentPrice']).toBe(10);
      expect(data['description']).toBe('opis');
      expect(data['endDateTime']).toBe('01.01.2018:13:10:15');
    });

    const req = httpMock.expectOne('api/auctions/1', 'put call');
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(auction);

    req.flush({
      id: 1,
      title: 'Skarpety',
      description: 'opis',
      currentPrice: 10,
      endDateTime: '01.01.2018:13:10:15'
    });
  });

  it('should create new auction with POST', () => {
    const auction: Auction = { title: 'Skarpety', description: 'opis',
      currentPrice: 10, endDateTime: '01.01.2018:13:10:15'} as Auction;

    service.createAuction(auction).subscribe( (data) =>{
      expect(data['id']).toBe(1);
      expect(data['title']).toBe('Skarpety');
      expect(data['currentPrice']).toBe(10);
      expect(data['description']).toBe('opis');
      expect(data['endDateTime']).toBe('01.01.2018:13:10:15');
    });

    const req = httpMock.expectOne('api/auctions', 'post call');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ title: 'Skarpety', description: 'opis', currentPrice: 10, endDateTime: '01.01.2018:13:10:15' });

    req.flush({
      id: 1,
      title: 'Skarpety',
      content: 'opis',
      currentPrice: 10,
      endDateTime: '01.01.2018:13:10:15'
    })
  });

  it('should DELETE correct auction given auction', () => {
    const auction: Auction = { id: 1, title: 'Skarpety', description: 'opis',
      currentPrice: 10, endDateTime: '01.01.2018:13:10:15'} as Auction;

    service.deleteAuction(auction).subscribe();

    const req = httpMock.expectOne('api/auctions/1', 'delete call');

    expect(req.request.method).toBe('DELETE');
    expect(req.request.body).toEqual(null);
  });

  it('should DELETE correct auction given id', () => {
    const auction: number = 1;

    service.deleteAuction(auction).subscribe();

    const req = httpMock.expectOne('api/auctions/1', 'delete call');

    expect(req.request.method).toBe('DELETE');
    expect(req.request.body).toEqual(null);
  });
});

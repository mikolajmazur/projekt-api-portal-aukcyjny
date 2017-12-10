import { TestBed, inject } from '@angular/core/testing';

import { InMemoryAuctionService } from './in-memory-auction.service';

describe('InMemoryAuctionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InMemoryAuctionService]
    });
  });

  it('should be created', inject([InMemoryAuctionService], (service: InMemoryAuctionService) => {
    expect(service).toBeTruthy();
  }));
});

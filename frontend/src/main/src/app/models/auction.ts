import { AuctionOffer } from "./auctionOffer";

export class Auction {
  id: number;
  title: string;
  currentPrice: number;
  endDateTime: string;
  description: string;
  photoUrl: string;
  offers: AuctionOffer[];
}

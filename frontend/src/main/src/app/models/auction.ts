import { AuctionOffer } from "./auctionOffer";
import { Category } from "./categoryModel";

export class Auction {
  id: number;
  title: string;
  currentPrice: number;
  minimalBid: number;
  endDateTime: Date;
  startDateTime: Date;
  description: string;
  photoUrl: string;
  sellerId: number;
  sellerUsername: string;
  offers: AuctionOffer[];
  containingCategories: Category[];
}

import {Category} from "./categoryModel";
import {AuctionListItem} from "./auctionListItem";

export class AuctionsPage {
  content: AuctionListItem[];
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  numberOfElements: number;
}

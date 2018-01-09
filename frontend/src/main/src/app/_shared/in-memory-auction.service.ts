import { InMemoryDbService} from 'angular-in-memory-web-api';


export class InMemoryAuctionService implements InMemoryDbService {
  createDb() {
    const auctions = [
      { id: 1,
        title: 'Kompjuter',
        content: 'Super duper komputer!!!',
        currentPrice: 1000,
        endDateTime: '01.01.2018:13:10:15'},
      { id: 2,
        title: 'Zegarek',
        content: 'opis',
        currentPrice: 100,
        endDateTime: '13.05.2018:13:10:15'},
      { id: 3,
        title: 'Kubek',
        content: 'opis',
        currentPrice: 15.2,
        endDateTime: '12.12.2017:15:10:18'}
    ];

    return {auctions};
  }
}

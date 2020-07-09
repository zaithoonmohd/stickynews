import { Component, OnInit } from '@angular/core';
import { News } from '../../news';
import { NewsappService } from '../../newsapp.service';

@Component({
  selector: 'newsapp-newslist',
  templateUrl: './newslist.component.html',
  styleUrls: ['./newslist.component.css']
})
export class NewslistComponent implements OnInit {

  newslist : Array<News>;
  // watchlist : Array<News>;

  constructor(private newsService : NewsappService) { 

    this.newslist = [];
    // this.watchlist = [];
  }

  ngOnInit() {

    this.newsService.getNews().subscribe(
      (newslist)=>{
        this.newslist.push(...newslist);
      }
    )
// this.newsService.getMyNews().subscribe(
//   (watchlist)=>{
//     this.watchlist.push(...watchlist);
//   }
// )
  }

}

import { Component, OnInit, Input } from '@angular/core';
import { News } from '../../news';
import { NewsappService } from '../../newsapp.service';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'newsapp-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  newslist : Array<News>;
  // @Input()
  // watchlist : Array<News>;

  constructor(private newsService : NewsappService,private authSer :AuthenticationService, private matSnackBar :MatSnackBar) { }

  ngOnInit(): void {
  }

  //adding news button to watchlist
  addNews(news){
    console.log("inside addNews to watchlist- news obj:",news);
      this.newsService.addToWatchList(news).subscribe((news)=>{
        console.log("news",news);
      this.matSnackBar.open('News added to watchlist!','',{duration:2000});
    },
    (err)=>{
      console.log("error message",err.error);
      this.matSnackBar.open(err.error,'',{duration:2000})
    });
  
  }

  //deleting news from watchlist
  deleteNews(news){
    console.log("inside deleteNews from watchlist- news obj:",news);
    this.newsService.deleteFromWatchList(news).subscribe((news)=>{
      this.matSnackBar.open('News deleted from your watchlist','',{duration:2000})
    },
    (err)=>{
      console.log("error message",err.error);
      this.matSnackBar.open(err.error,'',{duration:2000})
    });
  
    for (var i = 0; i < this.newslist.length; i++) {
      if (this.newslist[i].title == news.title) {
        this.newslist.splice(i, 1);
      }
    }
  } // deletenews
}



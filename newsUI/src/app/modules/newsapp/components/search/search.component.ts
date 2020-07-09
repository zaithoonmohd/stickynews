import { Component, OnInit } from '@angular/core';
import {NewsappService} from '../../newsapp.service';
import {News} from '../../news';
import { NgIf } from '@angular/common';

@Component({
  selector: 'newsapp-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  newslist :Array<News>;
 // templist :Array<News>;
  constructor(private newsService: NewsappService) {
   // this.templist=[];
   }

   onEnter(searchKey){
    this.newsService.searchNews(searchKey).subscribe(templist=>{
      console.log('NewsList from Api', templist);
      this.newslist =templist;
      // for (let newsval of this.templist) {
      //     console.log('newsval :', newsval)
      //       if(newsval['title'].indexOf(searchKey)!=-1){
      //         this.newslist = this.templist;
      //       }
      // }
        
    });
}
  ngOnInit() {
    
  }
}

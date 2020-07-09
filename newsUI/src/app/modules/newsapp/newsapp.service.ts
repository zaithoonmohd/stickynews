import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { News } from './news';
import { map } from 'rxjs/operators';
import { AuthenticationService } from '../authentication/authentication.service';
@Injectable({
  providedIn: 'root'
})
export class NewsappService {

  newsApiEndpoint : string;
  apiKey : string;

  watchListUrl : string;


  constructor(private http: HttpClient, private authServe : AuthenticationService) {

    this.apiKey="6fe15f6f12ef4a5c98b3d23cb3f70254";
    
    this.newsApiEndpoint="https://newsapi.org/v2/top-headlines";
   
    // this.watchListUrl="http://localhost:9096/FavouriteService/api/v1/news";
    this.watchListUrl="http://localhost:8088/api/v1/news";

  
   }

   //https://newsapi.org/v2/top-headlines?country=in&apikey=cbff6bcd88c74c8ba09d5b721edb9d09&page=1

   getNews():Observable<Array<News>>{
     console.log("Inside get all news from api");
    const url=`${this.newsApiEndpoint}?country=in&apikey=${this.apiKey}&page=1`;
   return this.http.get<Array<News>>(url).pipe(
     map(this.pickMovieResults)
 
 );
 }
pickMovieResults(response) {
  return response['articles'];
}

//get news from watchlist
  getMyNews():Observable<Array<News>>{
    let tok=this.authServe.getToken();
    console.log("Inside getMyNews -- for watchlist -token :",tok);

    let userid = sessionStorage.getItem('userid');
    console.log("user id from session:",userid);
    const url = `${this.watchListUrl}/watchnews/${userid}`;
    return this.http.get<Array<News>>(url,{
      headers: new HttpHeaders().set("Authorization",`Bearer ${tok}`)
    });
    //.pipe(map(this.pickResults)

    // .subscribe(
    //   (res)=>
    //   {
    //     this.notes=res;
    //     this.notesubject.next(this.notes);
    //   }
    // )
  }

  //{reportProgress:true,responseType:'text'}
//add news to watchlist
  addToWatchList(news){
    

    newNews :News;
    let newNews = news;

    const token = sessionStorage.getItem('bearerToken');
    console.log("token from session",token);

    let tok=this.authServe.getToken();
    console.log("in addToWatchList : news ",news);
    console.log("Token in addToWatchList :",tok);

    let userid = sessionStorage.getItem('userid');
    console.log("user id from session:",userid);
   // newNews.id = userid;
    //newNews.userId = userid;
    let newsidgen = this.generateNewId();
    console.log("generated id is ",newsidgen);
    newNews.newsId = newsidgen;
    console.log("news obj new ",newNews);
    const url = `${this.watchListUrl}/${userid}`;
    return this.http.post(url,newNews,{
      headers: new HttpHeaders().set("Authorization",`Bearer ${tok}`)
    });

    //.map( (res)=> {return (res);})
  }

   generateNewId(){
    let idIndex = Math.floor(Math.random() * 9999) + 1;
   //let value = { 2071 : {id :101, name: "bathri" , age:22}}
    //  if(Object.keys(value).includes(idIndex) == idIndex){
    //    idIndex = generateNewId()
    //  }
     return idIndex;
   }

  //{responseType:'text'}
  //delete news from watchlist
  deleteFromWatchList(news){
    let tok=this.authServe.getToken();
    console.log("in deleteFromWatchList : news ",news);
    let userid = sessionStorage.getItem('userid');
    console.log("user id from session:",userid);
    const url =`${this.watchListUrl}/delete/${news.newsId}/${userid}`;
    return this.http.delete(url,{
      headers:new HttpHeaders().set("Authorization",`Bearer ${tok}`)
    });
    // .subscribe(
    //   (res)=>
    //   {
        
    //     console.log("deleted successfuly");
    //   }
    // )
  }
  //search news
  searchNews(searchKey: string): Observable<Array<News>> {
      console.log("inside search-search key",searchKey);
    if (searchKey.length > 0) {
      //https://newsapi.org/v2/top-headlines?q=rahul&country=in&apikey=cbff6bcd88c74c8ba09d5b721edb9d09&page=1
     // const url=`${this.newsApiEndpoint}/search/${searchKey}`;
     const url=`${this.newsApiEndpoint}?q=${searchKey}&country=in&apikey=${this.apiKey}&page=1`;
      return this.http.get<Array<News>>(url)
      .pipe(map(this.pickMovieResults));
      }
      return null;
  }
  
  //update user -- checklater
  // updateUser(user){
  //     const url =`${this.newsApiEndpoint}/update/${user.userId}`;
  //   //replace this with the endpoint for user update
  //     console.log(user.userId,'updated user');
  //     return this.http.put(url, user);
    
  // }
}

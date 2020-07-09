import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { map } from 'rxjs/internal/operators/map';

import * as JWT from 'jwt-decode';
//@Injectable({
  //providedIn: 'root'
//})
@Injectable()
export class AuthenticationService {

  springEndPoint : string;

  uid:any;
  constructor(private httpClient:HttpClient) {
    //this.springEndPoint='http://localhost:9096/UserService/api/userservice';
    this.springEndPoint='http://localhost:9096/api/userservice';
    console.log("inside authenticationservice -constructor");

   }

   registerUser(newUser){
     console.log("inside register user");
     console.log("User inputs :",newUser);
     const url = this.springEndPoint+"/register";
     return this.httpClient.post(url,newUser,{reportProgress:true,responseType:'text'});

   }
   loginUser(newUser){
     console.log("Inside loginUser");
     console.log("User inputs :",newUser);
     const url = this.springEndPoint+"/login";
     return this.httpClient.post(url,newUser);
   }

  //  getItems() {
  //   this.http.get('https://example.com/api/items').pipe(map(data => {})).subscribe(result => {
  //     console.log(result);
  //   });
  // }

   setToken(token:string){
     console.log("Token set :",token);
    return sessionStorage.setItem('bearerToken',token);
    
  }

  getToken(){
    return sessionStorage.getItem('bearerToken');
  }

  deleteToken(){
    return sessionStorage.removeItem('bearerToken');
  }

  deleteUser(){
    return sessionStorage.removeItem('userid');
  }
  // getTokenExpirationDate(token:string):Date{
  //   const decoded = jwt_decode(token);
  //   if(decoded.exp ==undefined)
  //    return null;
  //   const date = new Date(0);
  //   date.setUTCSeconds(decoded.exp);
  //   return date;
  // }

  setUserid(token:string){
    console.log("Token set :",token);
    this. uid = JWT(token);
    console.log("Jwt decoded",this.uid);
    let user = this.uid.sub;
   return sessionStorage.setItem('userid',this.uid.sub);
   
 }
  getUserid(){
    return sessionStorage.getItem('userid');
  }

  isTokenExpired(token?:string):boolean{
    if(!token)
      token=this.getToken();
    if(!token)
    return true;
    else
    return false;
    //const date =this.getTokenExpirationDate(token);
    //if(date==undefined || date==null) return false;
    //return !(date.valueOf() > new Date().valueOf());
  }

  isLoggedIn(){
    const token= this.getToken();
    console.log("Token in isLoggedIn",token);
    if(token == null){
      return false;
  }else{
      return true;
  }
}
}

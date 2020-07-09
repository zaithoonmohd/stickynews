import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from './modules/authentication/authentication.service';

//@Injectable({
  //providedIn: 'root'
//})
@Injectable()
export class AuthGuardService  implements CanActivate{

  //routeparam :string;
  constructor(private router : Router, private authService:AuthenticationService, private activatedRouter : ActivatedRoute) { }
 
  canActivate(){

    console.log("Inside authguard");
    if(!this.authService.isTokenExpired()){
      return true;
    }
    this.router.navigate(['/login']);
  }

  appnews
  }


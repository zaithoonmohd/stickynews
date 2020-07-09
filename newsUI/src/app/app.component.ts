import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../app/modules/authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'newsUI';
  isMenuRequired:boolean;

  constructor(public authService : AuthenticationService, private routes : Router){
    this.isMenuRequired = false;

  }
  Logout(){
    console.log("logout action triggered");
    this.authService.deleteToken();
    this.authService.deleteUser();
    this.routes.navigate(['/login']);
    console.log("navigated to login");
  }

  ngOnInit(){
    console.log("Initializing -- appcomponent");
  }
}

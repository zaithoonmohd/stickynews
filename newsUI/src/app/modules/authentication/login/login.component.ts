import { Component, OnInit } from '@angular/core';
import {User} from './../User';
import {AuthenticationService} from './../authentication.service';
import{Router} from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'auth-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  newUser:User;

  constructor(private authservice:AuthenticationService, private router: Router, private matSnackBar :MatSnackBar) {

    console.log("Inside logincomp -constructor");
    this.newUser = new User();
   }

  ngOnInit() {
  }

  loginUser(){

    let data={
      userid:this.newUser.userId,
      password:this.newUser.password
    };

    console.log("Inside loginUser()");
    this.authservice.loginUser(data).subscribe((data)=>{
      if(data['token']){
        console.log("Token value in login user",data['token']);
        this.authservice.setToken(data['token']);
        this.authservice.setUserid(data['token']);
        console.log("Login success -- set --token --route to newsappservice");

        this.router.navigate(['news/allnews']);
      }
    },(err) => {
      console.log("error message",err.error);
      this.matSnackBar.open(err.error,'',{duration:2000})
    }
    )
  }
}

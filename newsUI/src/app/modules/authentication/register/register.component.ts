import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'auth-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser:User;
 

  constructor(private authService:AuthenticationService, private router:Router,private matSnackBar :MatSnackBar) { 
    console.log("Inside registercomp -constructor");
    this.newUser= new User();
  }
  ngOnInit() {
  }
  registerUser(){

    let data={
      firstname:this.newUser.firstName,
      lastname:this.newUser.lastName,
      userid:this.newUser.userId,
      password:this.newUser.password
    };

    console.log("New User values:",this.newUser)
    //if((this.newUser.firstName != null) && (this.newUser.lastName != null)&& (this.newUser.userId != null)&&(this.newUser.password != null)){
      this.authService.registerUser(data).subscribe((data)=>{
        console.log(data);
      this.matSnackBar.open('Registered user successfully!','',{duration:5000})
      this.router.navigate(['/login']);
    }, (err) => {
      console.log("error message",err.error);
      this.matSnackBar.open(err.error,'',{duration:2000})
    })
  //}

}
  resetInput(){
    //this.newUser=null;
      this.newUser.firstName='';
      this.newUser.lastName='';
      this.newUser.userId='';
      this.newUser.password='';
      
  }

}

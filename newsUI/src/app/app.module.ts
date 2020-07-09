import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
//import { AuthenticationComponent } from './authentication/authentication.component';
//import { NewsappComponent } from './newsapp/newsapp.component';
import { Routes, RouterModule } from '@angular/router';
import { MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
//import {MatDialogModule} from '@angular/material/dialog';
import {NewsappModule} from './modules/newsapp/newsapp.module';
import {AuthenticationModule} from './modules/authentication/authentication.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';

const appRoutes : Routes =[
  {
    path:'',
    redirectTo: 'login',
    pathMatch:'full'
  }
];
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    NewsappModule,
    AuthenticationModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatCardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

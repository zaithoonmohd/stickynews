import { NgModule } from '@angular/core';
//import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { NewslistComponent } from '../newsapp/components/newslist/newslist.component';


const authRoutes : Routes =[{

    path:'',
      children:[
        {
          path:'',
          redirectTo:'./news/allnews',
          pathMatch:'full'
        },
        // {
        //   path:'',
        //   redirectTo:'/login',
        //   pathMatch:'full'
        // },
        {
          path:'register',
          component:RegisterComponent,
        },
        {
          path:'login',
          component: LoginComponent,
        },
        {
          path:'./news/allnews',
          component : NewslistComponent,
        }
      ]
  }

];
@NgModule({
  declarations: [],
  imports: [
    //CommonModule
    RouterModule.forChild(authRoutes),
  ],
  exports:[
    RouterModule,
  ]
})
export class AuthenticationRoutingModule {
  
 }

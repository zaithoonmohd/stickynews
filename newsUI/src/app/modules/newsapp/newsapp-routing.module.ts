import { NgModule } from '@angular/core';
import {Routes,RouterModule} from '@angular/router';
import {NewslistComponent} from '../newsapp/components/newslist/newslist.component';

import {WatchlistComponent} from '../newsapp/components/watchlist/watchlist.component';
import {SearchComponent} from '../newsapp/components/search/search.component';
import {AuthGuardService} from '../../auth-guard.service';
import { UpdateComponent } from './components/update/update.component';

const newsAppRoutes :Routes =[  
  {
    path:'news',
    children:[
      {
        path :'',
        redirectTo:'/news/allnews',
        pathMatch:'full',
        canActivate:[AuthGuardService]
       
    },
        {
            path :'allnews',
            component :NewslistComponent,
            canActivate:[AuthGuardService],
            data:{
              routeparam:'allnews'
          },
          },
         
          {
            path:'watchlist',
            component: WatchlistComponent,
            canActivate:[AuthGuardService],
            data:{
              routeparam:'watchlist'
          },
          },{
            path:'search',
            component:SearchComponent,
            canActivate:[AuthGuardService],
            data:{
              routeparam:'search'
          },
          }
          ,
          {
            path:'update',
            component:UpdateComponent,
            canActivate:[AuthGuardService],
            data:{
              routeparam:'update'
          },
          }
        ]
      }
    
];



@NgModule({
    imports: [
     
      RouterModule.forRoot(newsAppRoutes)
    ],
    exports:[
        RouterModule
    ]
  })
  export class NewsAppRoutingModule { 
  
  
  
  }
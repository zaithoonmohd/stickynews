import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NewsAppRoutingModule} from '../newsapp/newsapp-routing.module';
import { ContainerComponent } from './components/container/container.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { NewslistComponent } from './components/newslist/newslist.component';
import {NewsappService} from './newsapp.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import{MatCardModule} from '@angular/material/card';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchComponent } from './components/search/search.component';
import {AuthGuardService} from '../../auth-guard.service';
import { DetailviewComponent } from './components/detailview/detailview.component';
import { UpdateComponent } from './components/update/update.component';



@NgModule({
  imports: [
    CommonModule,
    NewsAppRoutingModule,
    HttpClientModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    MatInputModule,
  ],
  entryComponents:[DetailviewComponent],
   declarations: [
     ContainerComponent, ThumbnailComponent, NewslistComponent, WatchlistComponent, SearchComponent,DetailviewComponent,UpdateComponent],
   exports:[ContainerComponent, ThumbnailComponent, NewslistComponent,NewsAppRoutingModule, WatchlistComponent, UpdateComponent,SearchComponent,DetailviewComponent],
   providers:[NewsappService,AuthGuardService]
})
export class NewsappModule { }

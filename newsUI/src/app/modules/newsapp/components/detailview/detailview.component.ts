import { Component, OnInit,Inject ,Output,EventEmitter } from '@angular/core';
import {News} from '../../news';
import {NewsappService} from '../../newsapp.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {AuthenticationService} from '../../../authentication/authentication.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'newsapp-detailview',
  templateUrl: './detailview.component.html',
  styleUrls: ['./detailview.component.css']
})
export class DetailviewComponent implements OnInit {
news:News;
description:string;

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any, public authServe : AuthenticationService) {
    this.news=data.obj;
    this.description= data.obj.description;
   }

  ngOnInit() {
  }

}


import { GameService } from 'src/app/services/game.service';
import { UserService } from 'src/app/services/user.service';

import { Observable, Subject, debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { Component, inject } from '@angular/core';
import { Games } from 'src/app/model/Games'
import { Router } from '@angular/router';

import {animate, state, style, transition, trigger} from '@angular/animations';
import { User } from 'src/app/model/User';
import {AddGamePopUpComponent } from '../add-game-pop-up/add-game-pop-up.component';

import {
  MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed,void', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('235ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ])],
})



export class GamesComponent  {


constructor(
private gameService:GameService,
private userService:UserService){}

games: Games[];

columnsToDisplay = ['title', 'dev', 'publisher', 'releaseDate'];
columnsToDisplayWithExpand = [...this.columnsToDisplay, 'expand'];
selectedGame: Games | null;
loading: boolean = false;

user: User | undefined;

dialog = inject(MatDialog);

ngOnInit(): void {
  this.user = JSON.parse(localStorage.getItem("user") || '{}');
}

openDialog(): void {
  const dialogRef = this.dialog.open(AddGamePopUpComponent);

  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
    if (result !== undefined) {
      
    }});
}

addToUser(game: Games) {
  if(this.user != undefined){
    this.userService.addGame(this.user, game).subscribe(
      (newUser) =>{
        this.user= newUser;
        console.log(this.user.games)
      }
    );
  }
}

searchByTitle(title:string): void {
  this.loading = true;
  this.gameService.searchGame(title).subscribe(returnedGames => {
    this.loading = false;
    this.selectedGame = null;
    this.games = returnedGames;
  });
  
  }

  


}

import { Component } from '@angular/core';
import { Games } from 'src/app/model/Games'
import { User } from 'src/app/model/User'

import { UserService } from 'src/app/services/user.service';
import {animate, state, style, transition, trigger} from '@angular/animations';

import { Observable } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed,void', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('235ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ])]
})
export class DashboardComponent {

  columnsToDisplay = ['title', 'dev', 'publisher', 'id'];
  columnsToDisplayWithExpand = [...this.columnsToDisplay, 'expand'];
  selectedGame: Games | null;
  loading: boolean = false;
  
  games: Games[];

  user: User | undefined;

  constructor(private userService:UserService){}

  ngOnInit(): void{
   
    this.loading = true;
    this.fetchEvent().subscribe(
      (user) => {
        if(user !=null){
        this.user = user;
        this.games = this.user.games as Games[];
        this.selectedGame = null;
      }
        this.loading = false;
      }

    );

  }

  fetchEvent(): Observable<User> {
    return this.userService.getUserByID(JSON.parse(localStorage.getItem("user") || '{}'));
  }


  search() {
    console.log(this.games);
  }

}

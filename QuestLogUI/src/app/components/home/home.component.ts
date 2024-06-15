import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {


  // ngOnInit(): void{
   
  //   this.loading = true;
  //   this.fetchEvent().subscribe(
  //     (user) => {
  //       if(user !=null){
  //       this.user = user;
  //       this.games = this.user.games as Games[];
  //       this.selectedGame = null;
  //     }
  //       this.loading = false;
  //     }

  //   );

  // }

  // fetchEvent(): Observable<User> {
  //   return this.gameService.getTopRated();
  // }
}

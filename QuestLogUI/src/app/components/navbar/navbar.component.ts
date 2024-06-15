import { Component } from '@angular/core';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  user: User | undefined;

  constructor(private api: UserService, public login:LoginComponent, private router:Router) {}

  userLoggedIn():Boolean{
    this.user = JSON.parse(localStorage.getItem("user") || '{}');
    if (this.user?.username == undefined){
      return false;
    }
    else{
      return true;
    }
  }

  logOut() {
    localStorage.removeItem("user");
    this.login.user = undefined;
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate(["/login"]);
    });
  }
}

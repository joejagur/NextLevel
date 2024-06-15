import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';

import { UserService } from 'src/app/services/user.service';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string;
  password: string;
  user?: User;
  constructor(private userService: UserService, private router:Router){}


login(username: string, password: string) {
  this.username = username;
  this.password = password;
  this.userService.getUser({username:this.username, password: this.password} as User).subscribe({
    next: (user) => {
      if(user) {
        this.user = user;
        localStorage.setItem("user", JSON.stringify(this.user));
        this.router.navigate(['/home']);
      }
    },
    error: (error: HttpErrorResponse) => {
      switch(error.status){
        case 409: {
          alert("Password Not Correct!");
          break;
        }
        case 404: {
          alert("Username Not Found!");
          break;
        }
      }
    }
  }
  )
  
}

moveToCreateAccountPage() {
  this.router.navigate(['/new'])
  }
}

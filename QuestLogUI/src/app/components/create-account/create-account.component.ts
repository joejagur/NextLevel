import { Component } from '@angular/core';
import {
  FormControl,
  FormGroupDirective,
  NgForm,
  Validators,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import {MatTooltip} from '@angular/material/tooltip';
import { HttpErrorResponse } from '@angular/common/http';
import { User } from 'src/app/model/User';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})

export class CreateAccountComponent {

  username: string = ""
  password: string = ""
  usernameFormControl = new FormControl('', [Validators.required]);
  password1FormControl = new FormControl('', [Validators.required]);
  password2FormControl = new FormControl('', [Validators.required]);
  
  matcher = new MyErrorStateMatcher();

  passwordsMatch: boolean = true;
  constructor(private userService: UserService, private router:Router){}

  createAccount(username: string, password: string ) {
    this.username = username;
    this.password = password;
    this.userService.createUser({username:this.username, password:this.password} as User).subscribe({
      next: (data) =>{
        this.router.navigate(['/home']);
      },
      error: (error: HttpErrorResponse) => {
        switch(error.status){
          case 406: {
            alert("Username already exist!");
            break;
          }
        }
      }    
  })
  }
}

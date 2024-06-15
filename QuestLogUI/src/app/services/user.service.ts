import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { User } from '../model/User';
import { Games } from '../model/Games';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userURL ='http://localhost:8080/user'
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(
    private http: HttpClient,
  ) { }

  createUser(newUser:User):Observable<User>{

    const url = `${this.userURL}/create`
    return this.http.post<User>(url, {"username": newUser.username, "password":newUser.password}, this.httpOptions);
  }
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

  getUser(userInfo: User):Observable<User>{
    const url = `${this.userURL}/name=${userInfo.username}&password=${userInfo.password}`
    console.log(url);
    return this.http.get<User>(url, this.httpOptions);
  }
  getUserByID(userInfo: User):Observable<User>{
    const url = `${this.userURL}/id=${userInfo.id}`
    return this.http.get<User>(url, this.httpOptions);
  }
  addGame(userInfo: User, gameInfo: Games): Observable<User> {
    const url = `${this.userURL}/addGame`;
    console.log(`URL: ${url}`);  // Log the URL to ensure it is correct
  
    const body = `{userID: ${userInfo.id},gameID: ${gameInfo.id}}`;
  
    return this.http.post<User>(url, body, this.httpOptions);
  }
}


import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { Games } from '../model/Games';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private gameURL ='http://localhost:8080/games'
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(
    private http: HttpClient,
  ) { }

  searchGame(title:string):Observable<Games[]>{
    if(!title.trim()){
      return of([])
    }

    const url = `${this.gameURL}/title=${title}`
    return this.http.get<Games[]>(url);
  }
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}


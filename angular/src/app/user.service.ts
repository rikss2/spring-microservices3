import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  authenticated = false;

  constructor(private http: HttpClient) {
  }

  headers?: HttpHeaders;
  private baseUrl = 'localhost:4200/api/';

  authenticate(credentials?: { username: string; password: string; }, callback?: () => any) {
    console.log(credentials);
    this.headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get('localhost:4200/api/login', {headers: this.headers}).subscribe(response => {
      if (response == 200) {
        this.authenticated = true;
      } else {
        this.authenticated = false;
      }
      return callback && callback();
    });

  }

  getAll(): Observable<User[]> {
    console.log(`${this.baseUrl}` + 'userlist');
    console.log(this.headers);
    return this.http.get<User[]>(`${this.baseUrl}` + 'userlist');
  }

  get(id: Number): Observable<User> {
    return this.http.get(`${this.baseUrl}` + 'user/' + id, {headers: this.headers});
  }

  create(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'user', user, {headers: this.headers});
  }

  update(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'user', user, {headers: this.headers});
  }

  delete(id: Number): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + 'user/' + id, {headers: this.headers});
  }


}

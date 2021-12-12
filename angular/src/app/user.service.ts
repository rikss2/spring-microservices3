import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}` + 'userlist');
  }

  get(id: Number): Observable<User> {
    return this.http.get(`${this.baseUrl}` + 'user/' + id);
  }

  create(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'user', user);
  }

  update(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'user', user);
  }

  delete(id: Number): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + 'user/' + id);
  }


}

import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/';

  constructor(
    private http: HttpClient) {
  }

  getUserList(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + 'userlist');
  }

  createUser(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'user', user);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/user/${id}`);
  }

  getUser(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/user/${id}`);
  }

  updateUser(user: User): Observable<Object> {
    return this.http.post(`${this.baseUrl}/user`, user);
  }
}

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./user";
import {credentialDTO} from "./CredentialDTO";
import {TokenDTO} from "./TokenDTO";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  headers?: HttpHeaders;
  private baseUrl = 'http://localhost:8080/';

  authenticate(credentials: credentialDTO) {
    this.login(credentials).subscribe((tokenDTO: TokenDTO) => {

      if (tokenDTO.token != null) {
        console.log("login successful, response:" + tokenDTO.token);
        this.headers = new HttpHeaders({'Authorization': "Bearer " + tokenDTO.token});
        console.log(this.headers);
      }
    })
  }

  login(credentials: credentialDTO): Observable<TokenDTO> {
    let token = this.http.post<TokenDTO>(this.baseUrl + 'login', credentials);
    return token;
  }

  getAll(): Observable<User[]> {


    return this.http.get<User[]>(this.baseUrl + 'userlist', {headers: this.headers})

  }

  get(id: Number): Observable<User> {
    return this.http.get(this.baseUrl + 'user' + id, {headers: this.headers});
  }

  create(user: User): Observable<any> {
    return this.http.post(this.baseUrl + 'user', user, {headers: this.headers});
  }

  update(user: User): Observable<any> {
    return this.http.post(this.baseUrl + 'user', user, {headers: this.headers});
  }

  delete(id: Number): Observable<any> {
    return this.http.delete(this.baseUrl + 'user' + id, {headers: this.headers});
  }


}

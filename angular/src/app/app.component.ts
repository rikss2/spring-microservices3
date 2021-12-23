import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserService} from "./user.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular';

  constructor(private userService: UserService, private http: HttpClient, private router: Router) {
  }

  logout() {
    this.http.post('localhost:4200/api/logout', {}).subscribe(() => {
      this.router.navigateByUrl('localhost:4200/api/login');
    });
  }
}

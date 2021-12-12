import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {AppService} from "../app.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentials = {username: '', password: ''};
  error: boolean = false;

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
  }

  login() {
    this.app.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/users');
    });
    return false;
  }
}

import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {UserService} from "../user.service";
import {credentialDTO} from "../CredentialDTO";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentials = new credentialDTO()
  error: boolean = false;

  constructor(private userService: UserService, private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
  }

  login() {
    this.userService.authenticate(this.credentials);
  }
}

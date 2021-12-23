import {Component, OnInit} from '@angular/core';
import {User} from "../user";
import {UserService} from "../user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-users',
  styleUrls: ['users.component.css'],
  templateUrl: 'users.component.html'
})
export class UsersComponent implements OnInit {
  displayedColumns: string[] = ["id", 'username', 'email', 'password'];
  dataSource?: User[];
  selectedUser: User = {};

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private router: Router,
              private http: HttpClient) {
  }

  ngOnInit(): void {
    this.retrive();
  }

  retrive(): void {
    this.userService.getAll()
      .subscribe({
        next: (data) => {
          this.dataSource = data
        },
        error: (e) => console.error(e)
      });
  }

  refresh(): void {
    this.retrive();
    this.selectedUser = {};
  }

  selectUser(user: User): void {
    this.refresh();
    console.log(user.id);
    this.selectedUser = user;
  }


  handleclick(user: User) {
    if (user.id == this.selectedUser.id) {
      this.selectedUser = {};
      return;
    }
    this.selectUser(user)
  }

  isSelected(user: User): boolean {
    if (user.id == this.selectedUser.id) {
      return true;
    }
    return false;
  }


}

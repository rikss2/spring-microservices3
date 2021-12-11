import {Component} from '@angular/core';
import {User} from "../user";
import {UserService} from "../user.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-users',
  styleUrls: ['users.component.css'],
  templateUrl: 'users.component.html'
})
export class UsersComponent {
  displayedColumns: string[] = ["id", 'username', 'email', 'password', 'editButton'];
  dataSource!: User[];
  userForm!: FormGroup;

  constructor(private userService: UserService) {

    userService.getUserList().subscribe(data => {
      this.dataSource = data
    });
  }

  editUser(user: User) {
    this.userForm = new FormGroup({
      username: new FormControl(user.username),
      email: new FormControl(user.email),
      password: new FormControl(user.password)
    })
  }

  cancel() {

  }

  save() {

  }
}


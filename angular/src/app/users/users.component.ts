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
  userForm?: FormGroup;
  editableUserId?: Number;

  constructor(private userService: UserService) {

    userService.getUserList().subscribe(data => {
      this.dataSource = data
    });
  }

  addUser() {
    this.editableUserId = undefined;
    let user = new User();
    this.userForm = new FormGroup({
      username: new FormControl(user.username),
      email: new FormControl(user.email),
      password: new FormControl(user.password)
    })
  }

  editUser(user: User) {
    this.editableUserId = user.id;
    this.userForm = new FormGroup({
      username: new FormControl(user.username),
      email: new FormControl(user.email),
      password: new FormControl(user.password)
    });
  }

  cancel() {
    this.editableUserId = undefined;
    this.userForm = undefined;
  }

  save() {
    if (!this.editableUserId) {
      this.userForm = undefined;
      return;
    }

    let id = this.userForm?.value.id;
    console.log(id);

    this.userForm = undefined;
    this.editableUserId = undefined;
  }

  deleteUser(id: Number) {
    this.userService.deleteUser(id).subscribe(data => console.log(data));
  }

}


import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../user";
import {UserService} from "../user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-userdetails',
  templateUrl: './userdetails.component.html',
  styleUrls: ['./userdetails.component.css']
})
export class UserdetailsComponent implements OnInit {

  @Input() selectedUser: User = {
    username: '',
    password: '',
    email: '',
  }
  @Output() reqRefresh = new EventEmitter();

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  updateUser(): void {

    this.userService.update(this.selectedUser)
      .subscribe({
        next: (res) => {
          console.log(res);
        },
        error: (e) => console.error(e)
      });
    this.reqRefresh.emit();
  }

  deleteUser(): void {
    this.userService.delete(this.selectedUser.id!)
      .subscribe({
        next: (res) => {
          console.log(res);
        },
        error: (e) => console.error(e)
      });
    this.reqRefresh.emit();
  }

  newUser() {
    this.selectedUser.id = -1;
  }
}

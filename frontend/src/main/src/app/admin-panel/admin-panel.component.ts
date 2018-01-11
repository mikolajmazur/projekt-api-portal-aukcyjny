import { Component, OnInit } from '@angular/core';
import {User} from "../_models/user";
import {UserService} from "../_service/user.service";

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {
  private users: User[];
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getUsers();
  }
  getUsers(){
    this.userService.getUsers().subscribe(data =>{
    this.users = data},
      error2 => {
      console.log(error2)
      });
  }
  isAdmin(user: User): boolean{
    let index = user.roles.indexOf("ROLE_ADMIN");
    return index !== -1;
  }
}

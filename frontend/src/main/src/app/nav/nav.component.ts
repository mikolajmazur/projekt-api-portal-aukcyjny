import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../_service/authentication.service";
import {Router} from "@angular/router";
import {CategoryService} from "../_service/category.service";
import {Category} from "../_models/categoryModel";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  username: string;
  private categories: Category[];
  private filteredCategories: Category[] = [];
  constructor(private router: Router,
              private authenthicationService: AuthenticationService,
              private categoryService: CategoryService) { }

  ngOnInit() {
    if (this.authenthicationService.isAuthenticated()){
      this.username = AuthenticationService.getUsername();
    }
    this.authenthicationService.username$.subscribe(data => this.username = data);
    this.loadCategories();
  }
  loadCategories(){
    this.categoryService.getCategories().subscribe(data => {
      data.forEach(c => {
        if (!c.parentId){
          this.filteredCategories.push(c);
        }});
    });
  }
  logout(){
    this.authenthicationService.logout();
    this.router.navigate(['/']);
  }
  isAdmin(): boolean{
    return this.authenthicationService.isAdmin();
  }
}

import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Category} from "../_models/categoryModel";
import {CategoryService} from "../_service/category.service";
import {current} from "codelyzer/util/syntaxKind";

class CategoryOption{
  name: string;
  id: number;
}

@Component({
  selector: 'app-add-auction',
  templateUrl: './add-auction.component.html',
  styleUrls: ['./add-auction.component.css']
})
export class AddAuctionComponent implements OnInit {
  private auctionForm: FormGroup;
  private durations: number[] = [7, 10, 14, 20];
  private defaultDuration = 7;
  private categories: Category[];
  private availableCategories: CategoryOption[] = [];
  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private categoryService: CategoryService) { }

  ngOnInit() {
    this.buildForm();
    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
      this.createCategoryOptions();
      this.auctionForm.controls['category'].setValue(this.availableCategories[0].id, {onlySelf: true});
    });
  }
  buildForm(){
    this.auctionForm = this.formBuilder.group({
      title: ['', Validators.required],
      startingPrice: ['', [Validators.required, Validators.min(1)]],
      description: ['', [Validators.required]],
      duration: [],
      category: []
    });
    this.auctionForm.controls['duration'].setValue(this.defaultDuration, {onlySelf: true});
  }
  createCategoryOptions(){
    this.categories.forEach(c => this.addCategoryOption("", c));
  }
  addCategoryOption(currentName: string, category: Category){
    if (currentName != ""){
      currentName = currentName + "->" + category.name;
    } else {
      currentName = category.name;
    }
    if (category.subCategories){
      category.subCategories.forEach(c => this.addCategoryOption(currentName, c));
    } else {
      this.availableCategories.push({name : currentName, id : category.id})
    }
  }
  onSubmit(form: FormGroup){
    console.log(form.value);
  }
}

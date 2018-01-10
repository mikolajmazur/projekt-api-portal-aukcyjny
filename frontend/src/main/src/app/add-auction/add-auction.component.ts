import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Category} from "../_models/categoryModel";
import {CategoryService} from "../_service/category.service";
import {current} from "codelyzer/util/syntaxKind";
import {AuctionService} from "../_service/auction.service";

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
  private categories: Category[];
  private availableCategories: CategoryOption[] = [];
  private editMode: boolean = false;
  private id: number;
  private loaded: boolean = false;
  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private categoryService: CategoryService,
              private auctionService: AuctionService,
              private route: ActivatedRoute) {

    if (route.snapshot.paramMap.get('id') != null){
      this.editMode = true;
      this.id = +route.snapshot.paramMap.get('id');
    }
  }

  ngOnInit() {
    this.loaded = false;
    this.buildForm();
    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
      this.createCategoryOptions();
      this.auctionForm.controls['category'].setValue(this.availableCategories[0].id, {onlySelf: true});
      if (this.editMode){
        this.initForm();
      } else {
        this.loaded = true;
      }
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
    this.auctionForm.controls['duration'].setValue(this.durations[0], {onlySelf: true});
  }
  initForm(){
    // pobierz dane o edytowanej aukcji z serwera
    this.auctionService.getAuction(this.id).subscribe(auction => {
      this.auctionForm.controls['title'].setValue(auction.title);
      if (auction.offers && auction.offers.length > 0){
        this.auctionForm.controls['startingPrice'].disable({onlySelf: true});
      } else {
        this.auctionForm.controls['startingPrice'].setValue(auction.currentPrice);
        this.auctionForm.controls['startingPrice'].setValidators([Validators.required, Validators.min(auction.currentPrice)]);
      }
      this.auctionForm.controls['duration'].setValue(this.durations[this.durations.indexOf(auction.duration)], {onlySelf: true});
      this.auctionForm.controls['description'].setValue(auction.description);
      let category = this.availableCategories.find(c => c.id == auction.containingCategories[auction.containingCategories.length - 1].id);
      this.auctionForm.controls['category'].setValue(category.id, {onlySelf: true});
      this.loaded = true;
    });
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
    if (!this.editMode){
      this.auctionService.createAuction(form.value).subscribe(data =>{
        let id = +data.id;
        this.router.navigate([`/auction/${id}`]);
      });
    } else {
     this.auctionService.updateAuction(form.value, this.id).subscribe(data =>
       this.router.navigate([`/auction/${this.id}`]));
    }
  }
}

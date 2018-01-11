import {Component, OnDestroy, OnInit} from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { LangChangeEvent, TranslateService } from "@ngx-translate/core";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-http-error-page',
  templateUrl: './http-error-page.component.html',
  styleUrls: ['./http-error-page.component.css']
})
export class HttpErrorPageComponent implements OnInit, OnDestroy {
  private errorCode: string;
  private errorMessage: string;
  private translatorSubcription: Subscription;
  constructor(private route: ActivatedRoute,
              private translator: TranslateService) { }

  ngOnInit() {
    this.errorCode = this.route.snapshot.paramMap.get('code');
    this.errorMessage = this.errorCode;
    this.getTranslatedMessage();
    this.translatorSubcription = this.translator.onLangChange.subscribe((event: LangChangeEvent) => {
      //console.log(event.translations[this.errorCode]);
      this.errorMessage = event.translations[this.errorCode];
    });
  }
  getTranslatedMessage(){
    this.translator.get(this.errorCode)
      .subscribe(data => this.errorMessage = data);
  }
  ngOnDestroy(){
    this.translatorSubcription.unsubscribe();
  }
}

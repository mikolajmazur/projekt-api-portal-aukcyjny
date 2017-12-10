import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  translate: TranslateService;
  constructor(translate: TranslateService) {
    this.translate = translate;
    translate.setDefaultLang('pl');
  }
  switchLanguage(lang: string) {
    this.translate.use(lang);
  }
}

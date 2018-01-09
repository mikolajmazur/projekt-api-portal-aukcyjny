import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private translate: TranslateService) {
    this.translate.addLangs(["pl", "en"]);
    this.setDefaultLang();
  }
  setDefaultLang(){
    let browserLang = this.translate.getBrowserLang();
    let availableLangs = this.translate.getLangs();
    if (browserLang && (availableLangs.indexOf(browserLang) != -1) ){
      this.translate.setDefaultLang(browserLang);
    } else {
      this.translate.setDefaultLang('pl');
    }
    this.translate.setDefaultLang('pl');
  }
  switchLanguage(lang: string) {
    this.translate.use(lang);
  }
}

import { BrowserModule } from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';
import { FormsModule } from '@angular/forms';
import {
  HTTP_INTERCEPTORS, HttpClientModule, HttpEvent, HttpHandler, HttpInterceptor,
  HttpRequest
} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './main/components/app.component';
import { HeroesComponent } from './hero/components/heroes.component';
import { HeroDetailComponent } from './hero/components/hero-detail.component';
import { PageNotFoundComponent } from './main/components/page-not-found.component';
import {AppService} from "./app.service";
import {LoginComponent} from "./session/components/login.component";
import {Observable} from "rxjs/Observable";

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });

    return next.handle(xhr);
  }

}

@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent,
    HeroDetailComponent,
    LoginComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [AppService, {provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }

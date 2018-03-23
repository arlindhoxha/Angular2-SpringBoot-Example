import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './main/components/app.component';
import { HeroesComponent } from './hero/components/heroes.component';
import { HeroDetailComponent } from './hero/components/hero-detail.component';
import { PageNotFoundComponent } from './main/components/page-not-found.component';
import {LoginComponent} from "./session/components/login.component";

const appRoutes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home' },
  { path: 'home', component: HeroesComponent },
  { path: 'login', component: LoginComponent },
  { path: 'hero/:id', component: HeroDetailComponent },
  { path: '**', component: PageNotFoundComponent },
  { path: 'error', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }
    )
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule {}

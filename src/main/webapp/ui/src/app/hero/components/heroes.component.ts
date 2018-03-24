import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { Hero } from '../../models/hero'
import { Observable } from 'rxjs/Observable';
import {AppService} from "../../app.service";

@Component({
  selector: 'hero-table',
  templateUrl: '../templates/heroes.component.html',
  styleUrls: ['../styles/heroes.component.css']
})
export class HeroesComponent implements OnInit {
    private input: string;
    private isEmpty: boolean = false;
    private isEditing: boolean = false;
    private heroes: Hero[];

    public constructor(private app: AppService, private http: HttpClient, private router: Router) {}

    public ngOnInit(): void {
      this.http.get<Hero[]>('/heroes')
      .subscribe(data => {
        console.log(data);
        this.heroes = data;
      });
    }

    public authenticated() {
      return this.app.authenticated;
    }

    public goToHero(id: number) {

    }

    public checkInput() {
      if (!this.input) {
        this.isEmpty = true;
      } else {
        this.isEmpty = false;
      }
    }

    public delete(hero: Hero) {
      console.log(hero);

      this.http.post('/heroes/delete?id=' + hero.id, hero.id)
      .subscribe(data => {
        const index = this.heroes.indexOf(hero);
        this.heroes.splice(index, 1);
      });
    }

    public add(name: string) {
      console.log(name);

      this.http.post<Hero>('/heroes/add?name=' + name, name)
      .subscribe(data => {
        const hero = new Hero();
        hero.id = data.id;
        hero.name = data.name;
        this.heroes.push(data);
        name = '';
      });
    }

    public modify(hero: Hero) {
      this.isEditing = true;
    }

    public cancel() {
      this.isEditing = false;
    }
}

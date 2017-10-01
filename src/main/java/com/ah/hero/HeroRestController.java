package com.ah.hero;

import com.ah.hero.Hero;
import com.ah.hero.HeroDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arlind Hoxha on 7/10/2017.
 */

@RestController
public class HeroRestController {
    private HeroDAO dao;

    @RequestMapping(value = "/heroes", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Hero>> showHeroes() {
        dao = HeroDAO.createHeroDAO();

        ResponseEntity<List<Hero>> response = new ResponseEntity<List<Hero>>(dao.findAll(), HttpStatus.ACCEPTED);
        System.out.println(response);
        return response;
    }

    @RequestMapping(value = "/heroes/delete", consumes = "application/json", method = RequestMethod.POST)
    public void deleteHero(@RequestParam("id")long id) {
        System.out.println("Deleting hero with id: " + id);

        dao = HeroDAO.createHeroDAO();

        dao.deleteHero(id);
    }

    @RequestMapping(value = "/heroes/add", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Hero> addHero(@RequestParam("name") String name) {
        System.out.println(name);

        dao = HeroDAO.createHeroDAO();

        Hero hero = new Hero(name);

        dao.addHero(hero);

        ResponseEntity<Hero> response = new ResponseEntity<Hero>(hero, HttpStatus.OK);
        return response;
    }
}

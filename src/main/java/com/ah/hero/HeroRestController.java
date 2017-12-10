package com.ah.hero;

import com.ah.hero.Hero;
import com.ah.hero.HeroDAO;
import org.apache.log4j.Logger;
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

    private static Logger logger = Logger.getLogger(HeroRestController.class);

    private HeroDAO dao;

    @RequestMapping(value = "/heroes", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Hero>> showHeroes() {
        logger.info("Entered HeroRestController.showHeroes");

        dao = HeroDAO.createHeroDAO();

        ResponseEntity<List<Hero>> response = new ResponseEntity<List<Hero>>(dao.findAll(), HttpStatus.ACCEPTED);

        logger.info("Heroes: " + response);

        return response;
    }

    @RequestMapping(value = "/heroes/delete", consumes = "application/json", method = RequestMethod.POST)
    public void deleteHero(@RequestParam("id")long id) {
        logger.info("Deleting hero with id: " + id);

        dao = HeroDAO.createHeroDAO();

        dao.deleteHero(id);

        logger.info("Hero deleted");
    }

    @RequestMapping(value = "/heroes/add", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Hero> addHero(@RequestParam("name") String name) {
        logger.info("Adding hero: " + name);

        dao = HeroDAO.createHeroDAO();

        Hero hero = new Hero(name);

        dao.addHero(hero);

        ResponseEntity<Hero> response = new ResponseEntity<Hero>(hero, HttpStatus.OK);

        logger.info("Hero added successfully");

        return response;
    }
}

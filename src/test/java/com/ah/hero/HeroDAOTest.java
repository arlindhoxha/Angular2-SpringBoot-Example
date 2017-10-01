package com.ah.hero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Arlind Hoxha on 10/1/2017.
 */
public class HeroDAOTest {

    private HeroDAO dao = HeroDAO.createHeroDAO();

    @Test
    public void testAddHero() {
        Hero hero = new Hero("test");
        dao.addHero(hero);

        assertEquals(1, dao.findAll().size());
    }

}
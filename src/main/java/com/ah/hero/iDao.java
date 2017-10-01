package com.ah.hero;

import com.ah.hero.Hero;

import java.util.List;

/**
 * Created by Arlind Hoxha on 7/13/2017.
 */
public interface iDao {

    List<Hero> findAll();
    Hero findById(long id);
    Hero findByName(String name);
    boolean addHero(Hero hero);
    boolean updateHero(Hero hero);
    boolean deleteHero(long id);
}

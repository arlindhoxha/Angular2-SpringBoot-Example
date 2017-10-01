package com.ah.hero;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by Arlind Hoxha on 7/13/2017.
 */
public class HeroDAO implements iDao {

    private static final String QUERIES_PROPERTIES = "/queries.properties";
    private static final String FIND_ALL = "find_all";

    private static EntityManagerFactory emf;
    private static Properties queries;
    private static HeroDAO heroDAO;

    private HeroDAO() {
        try (InputStream is = HeroDAO.class.getResourceAsStream(QUERIES_PROPERTIES)) {
            queries = new Properties();
            queries.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        emf = Persistence.createEntityManagerFactory("hero");
    }

    public static HeroDAO createHeroDAO() {
        if (heroDAO == null) {
            heroDAO = new HeroDAO();
        }

        return heroDAO;
    }

    @Override
    public List<Hero> findAll() {
        TypedQuery<Hero> query = getEntityManager().createQuery(queries.getProperty(FIND_ALL), Hero.class);

        return query.getResultList();
    }

    @Override
    public Hero findById(long id) {
        EntityManager em = getEntityManager();

        Hero hero = null;

        try {
            hero = em.find(Hero.class, id);
        } finally {
            em.close();
        }

        return hero;
    }

    @Override
    public Hero findByName(String name) {
        EntityManager em = getEntityManager();

        Hero hero = null;

        try {
            hero = em.find(Hero.class, name);
        } finally {
            em.close();
        }

        return hero;
    }

    @Override
    public boolean addHero(Hero hero) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            em.persist(hero);
            et.commit();

            return true;
        } catch (RollbackException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return false;
    }

    @Override
    public boolean updateHero(Hero hero) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        Hero existingHero = findById(hero.getId());

        try {
            if (existingHero != null) {
                et.begin();
                existingHero.setName(hero.getName());
                et.commit();

                return true;
            }
        } finally {
            em.close();
        }

        return false;
    }

    @Override
    public boolean deleteHero(long id) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            Hero hero = findById(id);

            if (hero != null) {
                et.begin();
                em.remove(em.contains(hero) ? hero : em.merge(hero));
                et.commit();
                return true;
            }
        } finally {
            em.close();
        }

        return false;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}

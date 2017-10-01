package com.ah.hero;

import javax.persistence.*;

/**
 * Created by Arlind Hoxha on 7/10/2017.
 */

@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    public Hero() {
        super();
    }

    public Hero(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

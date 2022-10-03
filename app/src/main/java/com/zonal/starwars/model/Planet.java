package com.zonal.starwars.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(active = true, generateConstructors = true, generateGettersSetters = true)
public class Planet {

    @Id
    private Long id;
    private String name;
    private String population;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 547733003)
    private transient PlanetDao myDao;

    /*
     Attempts were made to expand the details Planet holds from swapi, climate/orbital period etc. for display when the user selects an item from the planet list
     This, as expected, altered the generated Constructor to allow the new parameters to be stored in the database
     This was reverted as the alterations to the generated methods broke the database, and the code would no longer compile
     */

    public Planet(String name, String population){
        setName(name);
        setPopulation(population);
    }

    @Generated(hash = 1456603452)
    public Planet(Long id, String name, String population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    @Generated(hash = 720757740)
    public Planet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1300886363)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlanetDao() : null;
    }
}

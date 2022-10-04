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
    private String rotation;
    private String orbit;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String url;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 547733003)
    private transient PlanetDao myDao;

    public Planet(String name, String population, String rotation, String orbit, String diameter, String climate, String gravity, String terrain, String url){
        setName(name);
        setPopulation(population);
        setRotation(rotation);
        setOrbit(orbit);
        setDiameter(diameter);
        setClimate(climate);
        setGravity(gravity);
        setTerrain(terrain);
        setUrl(url);
    }

    @Generated(hash = 1053928788)
    public Planet(Long id, String name, String population, String rotation, String orbit, String diameter, String climate, String gravity, String terrain,
            String url) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.rotation = rotation;
        this.orbit = orbit;
        this.diameter = diameter;
        this.climate = climate;
        this.gravity = gravity;
        this.terrain = terrain;
        this.url = url;
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

    public String getRotation() {
        return this.rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public String getOrbit() {
        return this.orbit;
    }

    public void setOrbit(String orbit) {
        this.orbit = orbit;
    }

    public String getDiameter() {
        return this.diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getClimate() {
        return this.climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGravity() {
        return this.gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getTerrain() {
        return this.terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1300886363)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlanetDao() : null;
    }
}

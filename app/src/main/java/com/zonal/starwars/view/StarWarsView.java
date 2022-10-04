package com.zonal.starwars.view;

import com.zonal.starwars.model.Planet;

import java.util.List;

public interface StarWarsView {
    void setPlanets(List<Planet> planetList);
    void openPlanetDetails(Planet planet);
    void updatePlanet(Planet planet);
}

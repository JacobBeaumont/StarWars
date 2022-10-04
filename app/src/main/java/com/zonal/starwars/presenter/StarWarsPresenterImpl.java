package com.zonal.starwars.presenter;


import android.app.Activity;
import android.os.Handler;
import android.os.Looper;


import com.zonal.starwars.StarWarsApp;
import com.zonal.starwars.model.DaoSession;
import com.zonal.starwars.model.Planet;
import com.zonal.starwars.view.StarWarsView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class StarWarsPresenterImpl implements StarWarsPresenter {


    private StarWarsView starWarsView;
    private Activity activity;
    private String API_URL = "https://swapi.dev/api/planets";
    private List<Planet> planetList = new ArrayList<>();

    public StarWarsPresenterImpl(StarWarsView starWarsView, Activity activity) {
        this.starWarsView = starWarsView;
        this.activity = activity;
        getPlanets(API_URL);
    }


    private void getPlanets(final String urlName) {

        Thread thread = new Thread() {
            @Override
            public void run() {

                try {
                    URL url = new URL(urlName);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    url.openStream()));
                    String inputLine;
                    StringBuilder planets = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        planets.append(inputLine);
                    }

                    JSONObject jsonObject = new JSONObject(planets.toString());
                    parsePlanetsResponse(jsonObject);

                    in.close();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

    }

    private void getPlanetDetails(final String urlName) {
        Thread thread = new Thread() {
            @Override
            public void run() {

                try {
                    URL url = new URL(urlName);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    url.openStream()));
                    String inputLine;
                    StringBuilder planets = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        planets.append(inputLine);
                    }

                    JSONObject jsonObject = new JSONObject(planets.toString());
                    parsePlanetResponse(jsonObject);

                    in.close();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    private void parsePlanetsResponse(final JSONObject response) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                DaoSession daoSession = ((StarWarsApp) activity.getApplication()).getDaoSession();
                try {
                    JSONArray jsonArrayPlanets = response.getJSONArray("results");
                    planetList = new ArrayList<>();

                    //loop through all planets
                    for (int i = 0; i < jsonArrayPlanets.length() - 1; i++) {
                        JSONObject planetJson = jsonArrayPlanets.getJSONObject(i);
                        Planet planet = new Planet(planetJson.getString("name"), planetJson.getString("population"), planetJson.getString("rotation_period"), planetJson.getString("orbital_period"), planetJson.getString("diameter"), planetJson.getString("climate"), planetJson.getString("gravity"), planetJson.getString("terrain"), planetJson.getString("url"));
                        planetList.add(planet);
                        daoSession.insertOrReplace(planet);
                    }

                    starWarsView.setPlanets(planetList);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }

    private void parsePlanetResponse(final JSONObject response) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                DaoSession daoSession = ((StarWarsApp) activity.getApplication()).getDaoSession();
                try {
                    Planet planet = new Planet(response.getString("name"), response.getString("population"), response.getString("rotation_period"), response.getString("orbital_period"), response.getString("diameter"), response.getString("climate"), response.getString("gravity"), response.getString("terrain"), response.getString("url"));
                    daoSession.insertOrReplace(planet);

                    starWarsView.updatePlanet(planet);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }

    // Makes a call to retrieve the planets
    @Override
    public void refreshList() {
        getPlanets(API_URL);
    }

    @Override
    public void refreshPlanet(String url) {
        getPlanetDetails(url);
    }

    // Sorts the planet list depending on user choice and updates the StarWarsAdapter with the new list
    @Override
    public void sortList(final int sort) {
        Collections.sort(planetList, new Comparator<Planet>() {
            public int compare(Planet planet1, Planet planet2) {
                switch (sort) {
                    case StarWarsPresenter.name_asc:
                        return planet1.getName().compareToIgnoreCase(planet2.getName()); // To compare string values
                    case StarWarsPresenter.name_desc:
                        return planet2.getName().compareToIgnoreCase(planet1.getName()); // To compare string values
                }
                return 0;
            }
        });
        starWarsView.setPlanets(planetList);
    }

    // Retrieves requested Planet from the list and calls to display result in a Dialog
    @Override
    public void getPlanet(int position) {
        Planet planet = planetList.get(position);
        starWarsView.openPlanetDetails(planet);
    }
}

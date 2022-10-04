package com.zonal.starwars.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.zonal.starwars.R;
import com.zonal.starwars.model.Planet;
import com.zonal.starwars.presenter.StarWarsPresenter;
import com.zonal.starwars.presenter.StarWarsPresenterImpl;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class PlanetDetailsActivity extends AppCompatActivity implements StarWarsView {

    SwipeRefreshLayout swipeRefreshLayout;
    private StarWarsPresenter starWarsPresenter;
    private String planetUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planetdetails);

        starWarsPresenter = new StarWarsPresenterImpl(this, this);

        // Adding a back button to the navigation bar to finish the activity
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String planetName = intent.getStringExtra("name");
        String population = intent.getStringExtra("population");
        String rotation = intent.getStringExtra("rotation");
        String orbit = intent.getStringExtra("orbit");
        String diameter = intent.getStringExtra("diameter");
        String climate = intent.getStringExtra("climate");
        String gravity = intent.getStringExtra("gravity");
        String terrain = intent.getStringExtra("terrain");
        this.planetUrl = intent.getStringExtra("planetUrl");

        swipeRefreshLayout = findViewById(R.id.swipeRefreshDetails);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                starWarsPresenter.refreshPlanet(planetUrl);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        setPlanetDetails(planetName, population, rotation, orbit, diameter, climate, gravity, terrain);
    }

    private void setPlanetDetails(String planetName, String population, String rotation, String orbit, String diameter, String climate, String gravity, String terrain) {
        TextView populationTextView = findViewById(R.id.population);
        TextView rotationTextView = findViewById(R.id.rotation);
        TextView orbitTextView = findViewById(R.id.orbit);
        TextView diameterTextView = findViewById(R.id.diameter);
        TextView climateTextView = findViewById(R.id.climate);
        TextView gravityTextView = findViewById(R.id.gravity);
        TextView terrainTextView = findViewById(R.id.terrain);

        setTitle(planetName);

        populationTextView.setText("Current population: " + population);
        rotationTextView.setText("Rotation Period (Day length): " + rotation);
        orbitTextView.setText("Orbital Period (Year Length): " + orbit);
        diameterTextView.setText("Diameter: " + diameter);
        climateTextView.setText("Climate: " + climate);
        gravityTextView.setText("Gravity: " + gravity);
        terrainTextView.setText("Terrain: " + terrain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.planet_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.refreshPlanet:
                starWarsPresenter.refreshPlanet(planetUrl);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setPlanets(List<Planet> planetList) {
        // We don't do anything here
    }

    @Override
    public void openPlanetDetails(Planet planet) {
        // We don't do anything here
    }

    @Override
    public void updatePlanet(Planet planet) {
        setPlanetDetails(planet.getName(), planet.getPopulation(), planet.getRotation(), planet.getOrbit(), planet.getDiameter(), planet.getClimate(), planet.getGravity(), planet.getTerrain());
        this.planetUrl = planet.getUrl();
    }
}

package com.zonal.starwars.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zonal.starwars.PlanetDialog;
import com.zonal.starwars.R;
import com.zonal.starwars.model.Planet;
import com.zonal.starwars.presenter.StarWarsPresenter;
import com.zonal.starwars.presenter.StarWarsPresenterImpl;

import java.util.List;


public class StarWarsActivity extends AppCompatActivity implements StarWarsView, StarWarsAdapter.OnItemClickListener {

    private StarWarsPresenter starWarsPresenter;
    private RecyclerView recyclerView;
    private StarWarsAdapter starWarsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starwars);
        recyclerView = findViewById(R.id.recyclerViewPlanets);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshPlanets);
        setAdapter();
        starWarsPresenter = new StarWarsPresenterImpl(this, this);

        // Calls the Presenter to refresh the Planet list on a swipe refresh, removes animation on conclusion
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                starWarsPresenter.refreshList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        starWarsAdapter = new StarWarsAdapter(this);

        // Assigns the OnItemClick interface for the StarWarsAdapter list items
        starWarsAdapter.setOnClickListener(this);
        recyclerView.setAdapter(starWarsAdapter);
    }

    @Override
    public void setPlanetDialog(Planet planet) {
        // Displays a PlanetDialog populated with data from "planet"
        PlanetDialog planetDialog = new PlanetDialog(StarWarsActivity.this, planet);
        planetDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.star_wars_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.name_asc:
                starWarsPresenter.sortList(starWarsPresenter.name_asc);
                return true;
            case R.id.name_desc:
                starWarsPresenter.sortList(starWarsPresenter.name_desc);
                return true;
            case R.id.refresh:
                starWarsPresenter.refreshList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void setPlanets(List<Planet> planetList) {
        starWarsAdapter.setPlanetList(planetList);
    }

    @Override
    public void onItemClick(View view, int position) {
        starWarsPresenter.getPlanet(position);
    }
}

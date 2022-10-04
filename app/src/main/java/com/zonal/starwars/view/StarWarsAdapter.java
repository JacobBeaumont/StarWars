package com.zonal.starwars.view;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zonal.starwars.R;
import com.zonal.starwars.model.DaoMaster;
import com.zonal.starwars.model.Planet;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter that handles basketable objects (e.g Basket Items and Vouchers)
 */
public class StarWarsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Planet> planetList = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;
    OnItemClickListener listener; // The callback listener to pass an id back to the StarWarsActivity

    StarWarsAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnClickListener(OnItemClickListener callback) {
        this.listener = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        final View lBasketableView = mLayoutInflater.inflate(R.layout.list_item_planet, parent, false);
        final StarWarsViewHolder starWarsViewHolder = new StarWarsViewHolder(lBasketableView);


        return starWarsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final StarWarsViewHolder starWarsViewHolder = (StarWarsViewHolder) holder;
        starWarsViewHolder.txtPlanetName.setText(planetList.get(position).getName());
        starWarsViewHolder.txtPlanetPopulation.setText("Population: " + planetList.get(position).getPopulation());

        // Returns the position of the selected starWarsViewHolder
        starWarsViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, starWarsViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }

    public void updateItem(Planet planet) {
        for(int i = 0; i < planetList.size(); i++) {
            if(planetList.get(i).getName().equals(planet.getName())){
                planetList.set(i, planet);
            }
        }
    }

    public void setPlanetList(List<Planet> items) {

        planetList.clear();
        planetList.addAll(items);
        Log.d("API8","items "+items.size());
        // Notify the adapter that the data set has changed.
        notifyDataSetChanged();



    }


    static class StarWarsViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        TextView txtPlanetName;
        TextView txtPlanetPopulation;


        StarWarsViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.list_item);
            txtPlanetName = itemView.findViewById(R.id.planetName);
            txtPlanetPopulation = itemView.findViewById(R.id.planetPopulation);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}

package com.zonal.starwars;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zonal.starwars.model.Planet;

import androidx.annotation.NonNull;


// Custom Dialog class for display Planet details
public class PlanetDialog extends Dialog implements View.OnClickListener{

    private final Planet planet;
    private TextView nameTextView;
    private TextView populationTextView;

    public PlanetDialog(@NonNull Context context, Planet planet) {
        super(context);
        this.planet = planet;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_planet);
        nameTextView = findViewById(R.id.nameTextView);
        populationTextView = findViewById(R.id.populationTextView);
        Button ok = findViewById(R.id.buttonOk);
        ok.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Assigning planet details to relevant TextViews
        nameTextView.setText(planet.getName());
        populationTextView.setText(planet.getPopulation());
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonOk){
            dismiss(); // Dismissing the dialog here, rather than overriding in the calling Activity
        }
    }
}

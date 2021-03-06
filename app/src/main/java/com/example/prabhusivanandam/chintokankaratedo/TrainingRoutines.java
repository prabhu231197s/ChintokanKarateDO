package com.example.prabhusivanandam.chintokankaratedo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Prabhu Sivanandam on 13-May-17.
 */

public class TrainingRoutines extends Fragment {

    CardView white_belt,yellow_belt,orange_belt,purple_belt,green_belt,brown_belt,black_belt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.training_routines,container,false);
        white_belt=(CardView)v.findViewById(R.id.white_belt);
        white_belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WhitebeltRoutine whitebeltRoutine=new WhitebeltRoutine();
                getFragmentManager().beginTransaction().replace(R.id.fr,whitebeltRoutine).commit();
            }
        });
        yellow_belt=(CardView)v.findViewById(R.id.yellow_belt);
        yellow_belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YellowbeltRoutine yellowbeltRoutine=new YellowbeltRoutine();
                getFragmentManager().beginTransaction().replace(R.id.fr,yellowbeltRoutine).commit();
            }
        });
        orange_belt=(CardView)v.findViewById(R.id.orange_belt);
        orange_belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrangebeltRoutine orangebeltRoutine=new OrangebeltRoutine();
                getFragmentManager().beginTransaction().replace(R.id.fr,orangebeltRoutine).commit();
            }
        });
        purple_belt=(CardView)v.findViewById(R.id.purple_belt);
        purple_belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurplebeltRoutine purplebeltRoutine=new PurplebeltRoutine();
                getFragmentManager().beginTransaction().replace(R.id.fr,purplebeltRoutine).commit();
            }
        });
        green_belt=(CardView)v.findViewById(R.id.green_belt);
        green_belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GreenbeltRoutine greenbeltRoutine=new GreenbeltRoutine();
                getFragmentManager().beginTransaction().replace(R.id.fr,greenbeltRoutine).commit();
            }
        });
        brown_belt=(CardView)v.findViewById(R.id.brown_belt);
        brown_belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrownbeltRoutine brownbeltRoutine=new BrownbeltRoutine();
                getFragmentManager().beginTransaction().replace(R.id.fr,brownbeltRoutine).commit();
            }
        });
        black_belt=(CardView)v.findViewById(R.id.black_belt);
        black_belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlackbeltRoutine blackbeltRoutine=new BlackbeltRoutine();
                getFragmentManager().beginTransaction().replace(R.id.fr,blackbeltRoutine).commit();
            }
        });
        return v;
    }

}

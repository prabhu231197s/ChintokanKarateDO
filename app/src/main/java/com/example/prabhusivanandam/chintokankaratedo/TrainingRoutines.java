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
        yellow_belt=(CardView)v.findViewById(R.id.yellow_belt);
        orange_belt=(CardView)v.findViewById(R.id.orange_belt);
        purple_belt=(CardView)v.findViewById(R.id.purple_belt);
        green_belt=(CardView)v.findViewById(R.id.green_belt);
        brown_belt=(CardView)v.findViewById(R.id.brown_belt);
        black_belt=(CardView)v.findViewById(R.id.black_belt);
        return v;
    }
}

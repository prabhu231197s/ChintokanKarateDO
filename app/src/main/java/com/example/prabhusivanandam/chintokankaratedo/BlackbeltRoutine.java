package com.example.prabhusivanandam.chintokankaratedo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Prabhu Sivanandam on 13-May-17.
 */

public class BlackbeltRoutine extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.blackbelt_routine,container,false);
        return v;
    }
}

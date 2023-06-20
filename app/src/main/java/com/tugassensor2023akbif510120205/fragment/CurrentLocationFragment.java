package com.tugassensor2023akbif510120205.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tugassensor2023akbif510120205.R;
import com.tugassensor2023akbif510120205.activity.CurrentLocationActivity;

public class CurrentLocationFragment extends Fragment {

    private View maps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_current_location, container, false);

        maps = rootView.findViewById(R.id.map);

        // Tampilkan activity lain di dalam fragment

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        showOtherActivity();
    }

    private void showOtherActivity() {
        Intent intent = new Intent(getActivity(), CurrentLocationActivity.class);
        startActivity(intent);
    }


}


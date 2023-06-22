package com.tugassensor2023akbif510120205.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tugassensor2023akbif510120205.R;
import com.tugassensor2023akbif510120205.databinding.ActivityMainBinding;
import com.tugassensor2023akbif510120205.fragment.CurrentLocationFragment;
import com.tugassensor2023akbif510120205.fragment.InfoFragment;
import com.tugassensor2023akbif510120205.fragment.NearbyPlacesFragment;
import com.tugassensor2023akbif510120205.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new InfoFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new InfoFragment());
                    return true;
                case R.id.location:
                    replaceFragment(new CurrentLocationFragment());
                    return true;
                case R.id.resto:
                    replaceFragment(new NearbyPlacesFragment());
                    return true;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    return true;
            }

            return false;
        });
    }



    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity, fragment);
        fragmentTransaction.commit();
    }
}

// 10120205 - Raya Adhary - IF5
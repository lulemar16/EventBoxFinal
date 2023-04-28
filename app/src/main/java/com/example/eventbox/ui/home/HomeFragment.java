package com.example.eventbox.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.eventbox.HomeSideBar;
import com.example.eventbox.PlanEventFragment;
import com.example.eventbox.R;
import com.example.eventbox.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // setupFragments();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    /*private void setupFragments() {
        // Create your fragments
        PlanEventFragment planEventFragment = new PlanEventFragment();
//        TakePictureFragment takePictureFragment = new TakePictureFragment();
//        ViewEventsFragment viewEventsFragment = new ViewEventsFragment();

        // Get a reference to the bottom navigation view
        BottomNavigationView navigationView = findViewById(R.id.navigationView);

        // Set up a listener for bottom navigation view item selection
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item selection
                switch (item.getItemId()) {
                    case R.id.navigation_plan_event:
                        // Show the PlanEventFragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, planEventFragment).commit();
                        return true;
//                    case R.id.navigation_take_picture:
//                        // Show the TakePictureFragment
//                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, takePictureFragment).commit();
//                        return true;
//                    case R.id.navigation_view_events:
//                        // Show the ViewEventsFragment
//                        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, viewEventsFragment).commit();
//                        return true;
                }
                return false;
            }
        });

        // Set the default fragment to display (in this case, the PlanEventFragment)
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, planEventFragment).commit();
    }*/

}
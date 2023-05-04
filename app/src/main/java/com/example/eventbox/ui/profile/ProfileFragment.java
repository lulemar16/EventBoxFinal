package com.example.eventbox.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.eventbox.MainActivity;
import com.example.eventbox.R;
import com.example.eventbox.UserModel;
import com.example.eventbox.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        UserModel current_user = MainActivity.getCurrentUser();
        String currentUserName = current_user.getName();
        String currentUserEmail = current_user.getEmail();

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        TextView userName = root.findViewById(R.id.userNameView);
        TextView email = root.findViewById(R.id.emailView);

        userName.setText(currentUserName);
        email.setText(currentUserEmail);

        binding.btnReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProfileFragment.this)
                        .navigate(R.id.action_nav_profile_to_nav_home);
            }
        });


        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
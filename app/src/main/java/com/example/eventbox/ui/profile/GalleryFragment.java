package com.example.eventbox.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.eventbox.R;

public class GalleryFragment extends Fragment {
    private TextView mUsernameTextView;
    private TextView mEmailTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mUsernameTextView = view.findViewById(R.id.username_text_view);
        mEmailTextView = view.findViewById(R.id.email_text_view);

        // Set the username and email values
        String username = "John Doe";
        String email = "johndoe@example.com";
        mUsernameTextView.setText(username);
        mEmailTextView.setText(email);

        // Set up the change password button
        Button changePasswordButton = view.findViewById(R.id.change_password_button);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the change password activity or fragment
                // ...
            }
        });

        return view;
    }
}
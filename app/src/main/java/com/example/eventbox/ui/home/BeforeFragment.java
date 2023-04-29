package com.example.eventbox.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.eventbox.R;

public class BeforeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_before, container, false);

        // Inflate the form layout
        View formView = inflater.inflate(R.layout.fragment_before, container, false);

        // Find the EditText fields and Button in the form layout
        EditText nameEditText = formView.findViewById(R.id.edit_text_name);
        EditText emailEditText = formView.findViewById(R.id.edit_text_email);
        Button submitButton = formView.findViewById(R.id.button_submit);

        // Set an OnClickListener for the submit Button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the values entered in the EditText fields
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();

                // TODO: Do something with the name and email values

                // Clear the EditText fields
                nameEditText.setText("");
                emailEditText.setText("");
            }
        });

        // Add the form layout to the rootView
        LinearLayout formContainer = rootView.findViewById(R.id.form_container);
        formContainer.addView(formView);

        return rootView;
    }

}

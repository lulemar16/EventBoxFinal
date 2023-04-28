package com.example.eventbox;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlanEventFragment extends Fragment {

    private EditText eventNameEditText;
    private EditText eventLocationEditText;
    private Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan_event, container, false);

        eventNameEditText = view.findViewById(R.id.eventNameEditText);
        eventLocationEditText = view.findViewById(R.id.eventLocationEditText);
        saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventName = eventNameEditText.getText().toString();
                String eventLocation = eventLocationEditText.getText().toString();

                // TODO: Save the event information and location to a database or shared preferences

                Toast.makeText(getActivity(), "Event saved!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

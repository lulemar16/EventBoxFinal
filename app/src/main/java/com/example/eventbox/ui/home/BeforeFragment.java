package com.example.eventbox.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.eventbox.DataBaseHelper;
import com.example.eventbox.EventModel;
import com.example.eventbox.R;

import java.util.List;

public class BeforeFragment extends Fragment {

    ListView eventList;

    List<EventModel> dbEvents;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_before, container, false);


        // Find the EditText fields and Button in the form layout
        EditText eventNameEditText = rootView.findViewById(R.id.edit_text_event_name);
        EditText eventDescEditText = rootView.findViewById(R.id.edit_text_event_description);
        EditText eventDateEditText = rootView.findViewById(R.id.edit_text_event_date);
        EditText eventPlaceEditText = rootView.findViewById(R.id.edit_text_event_place);
        Button addButton = rootView.findViewById(R.id.button_add);
        Button refreshButton = rootView.findViewById(R.id.button_refresh);

        //eventList = rootView.findViewById(R.id.eventList);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

        // Set an OnClickListener for the submit Button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the values entered in the EditText fields
                String eventName = eventNameEditText.getText().toString();
                String eventDesc = eventDescEditText.getText().toString();
                String eventDate = eventDateEditText.getText().toString();
                String eventPlace = eventPlaceEditText.getText().toString();

                // Do something with the values
                int nextId = dataBaseHelper.getNextIdEvents();
                EventModel event = new EventModel(nextId, eventName, eventDesc, eventDate, eventPlace);
                dataBaseHelper.addOneEvent(event);

                // Clear the EditText fields
                eventNameEditText.setText("");
                eventDescEditText.setText("");
                eventDateEditText.setText("");
                eventPlaceEditText.setText("");
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbEvents = dataBaseHelper.getEvents();
                ArrayAdapter<EventModel> eventArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dbEvents);
                eventList = rootView.findViewById(R.id.eventList);
                eventList.setAdapter(eventArrayAdapter);
            }
        });


        dbEvents = dataBaseHelper.getEvents();
        ArrayAdapter<EventModel> eventArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dbEvents);
        eventList = rootView.findViewById(R.id.eventList);
        eventList.setAdapter(eventArrayAdapter);


        return rootView;
    }

}

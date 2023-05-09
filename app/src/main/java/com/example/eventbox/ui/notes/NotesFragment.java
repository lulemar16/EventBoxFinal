package com.example.eventbox.ui.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.eventbox.DataBaseHelper;
import com.example.eventbox.EventModel;
import com.example.eventbox.NotesModel;
import com.example.eventbox.R;

import java.util.ArrayList;
import java.util.List;


public class NotesFragment extends Fragment {

    ListView notesList;
    List<NotesModel> dbNotes;
    ArrayAdapter<NotesModel> notesArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        // Find the EditText fields and Button in the form layout
        EditText noteEditText = rootView.findViewById(R.id.edit_text_note);
        Button addButton = rootView.findViewById(R.id.button_add);

        dataBaseHelper = new DataBaseHelper(getContext());

        // Initialize the ListView
        notesList = rootView.findViewById(R.id.notesList);
        dbNotes = new ArrayList<>();

        // Set up the adapter for the ListView
        notesArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dbNotes);
        notesList.setAdapter(notesArrayAdapter);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the values entered in the EditText fields
                String noteNote = noteEditText.getText().toString();

                // Do something with the value
                int nextId = dataBaseHelper.getNextIdNotes();
                NotesModel note = new NotesModel(nextId, noteNote);

                // Clear the EditText fields
                noteEditText.setText("");

                // Add the new note to the ListView and refresh it
                dbNotes.add(note);
                notesArrayAdapter.notifyDataSetChanged();
            }
        });


        // Set an OnItemClickListener for the individual items in the ListView
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Delete the selected note from the database
                NotesModel selectedNote = dbNotes.get(position);
                dataBaseHelper.deleteOneNote(selectedNote.getId());

                // Remove the selected note from the ListView and refresh it
                dbNotes.remove(position);
                notesArrayAdapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }

    // Load the notes from the database and display them in the ListView
    private void loadNotesFromDatabase() {
        dbNotes.clear();
        dbNotes.addAll(dataBaseHelper.getAllNotes());
        notesArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Save the notes in the ListView to the database
        for (NotesModel note : dbNotes) {
            dataBaseHelper.addOneNote(note);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadNotesFromDatabase();
    }

}

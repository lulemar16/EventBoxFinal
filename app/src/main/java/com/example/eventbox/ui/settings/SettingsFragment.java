package com.example.eventbox.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;

import com.example.eventbox.R;
import com.example.eventbox.databinding.SettingsFragmentBinding;

/*
public class SettingsFragment extends Fragment {

    private SettingsFragmentBinding binding;
    private SettingsViewModel mViewModel;
    private SharedPreferences mSharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        mSharedPreferences = mViewModel.getSharedPreferences(getContext());
        return view;
    }

    
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    private void setPreferencesFromResource(int root_preferences, String rootKey) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
*/

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}


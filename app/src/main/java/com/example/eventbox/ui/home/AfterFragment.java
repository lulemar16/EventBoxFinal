package com.example.eventbox.ui.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.eventbox.AlarmReceiver;
import com.example.eventbox.R;

import java.util.Calendar;

public class AfterFragment extends Fragment implements View.OnClickListener {

    private int notificationId = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_after, container, false);

        // Set onClick Listener
        rootView.findViewById(R.id.setBtn).setOnClickListener(this);
        rootView.findViewById(R.id.cancelBtn).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        EditText editText = getView().findViewById(R.id.editTextAlarm);
        TimePicker timePicker = getView().findViewById(R.id.timePicker);

        // Intent
        Context context = getContext();
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("message", editText.getText().toString());

        // PendingIntent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        );

        // AlarmManager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setBtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set Alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(context, "Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}

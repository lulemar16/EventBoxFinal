package com.example.eventbox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int batteryLevel = Math.round(((float) level / (float) scale) * 100);

        if (batteryLevel < 20) {
            Toast.makeText(context, "The battery level is low (" + batteryLevel + "%)", Toast.LENGTH_LONG).show();
        }
    }
}

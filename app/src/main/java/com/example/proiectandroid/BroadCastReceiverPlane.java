package com.example.proiectandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Chronometer;
import android.widget.Toast;

public class BroadCastReceiverPlane extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
            if (isAirplaneModeOn) {
                Toast.makeText(context, "Airplane mode is on", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Airplane mode is off", Toast.LENGTH_LONG).show();
            }
        }
    }


}

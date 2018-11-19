package com.example.sokol.battery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final int MAX_DRAWABLE_LEVEL = 10000;

    private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int maxCharge = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
            int charge = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            float value = charge / (float) maxCharge;

            int drawableLevel = (int) (MAX_DRAWABLE_LEVEL * value);
            batteryView.getDrawable().setLevel(drawableLevel);

            int percent = (int) (100 * value);

            percentView.setText(getResources().getQuantityString(R.plurals.percents, percent, percent));
        }
    };

    private ImageView batteryView;
    private TextView percentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        batteryView = findViewById(R.id.battery_view);
        percentView = findViewById(R.id.text_percent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(batteryReceiver);
    }
}

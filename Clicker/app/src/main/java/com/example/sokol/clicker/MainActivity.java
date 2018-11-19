package com.example.sokol.clicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String STATE_COUNT = "count";


    private TextView clicksView;
    private View buttonIncrement, buttonReset;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clicksView = findViewById(R.id.text_clicks);
        buttonIncrement = findViewById(R.id.button_increment);
        buttonReset = findViewById(R.id.button_reset);

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                showCounter();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                showCounter();
            }
        });

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(STATE_COUNT, 0);
        }

        showCounter();
    }

    private void showCounter() {
        clicksView.setText(Integer.toString(counter));
        buttonReset.setVisibility(counter > 0? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_COUNT, counter);
    }
}

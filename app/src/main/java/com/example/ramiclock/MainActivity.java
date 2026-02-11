package com.example.ramiclock;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView clockText;
    private TextView dateText;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clockText = findViewById(R.id.clock_text);
        dateText = findViewById(R.id.date_text);

        runnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
    }

    private void updateTime() {
        Date now = new Date();
        // KST formatting (or device locale)
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd (E)", Locale.KOREA);

        clockText.setText(timeFormat.format(now));
        dateText.setText(dateFormat.format(now));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}

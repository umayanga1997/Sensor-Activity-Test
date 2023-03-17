package com.openlab.traiing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView textView; // Need Name
    private SensorManager sensorManager; // Name is Fine
    private Sensor liSensor; // Need Name

    private MediaPlayer mp; // Need Name

    // My SNumber : xxxxxxxx
    // private static int ThuNo = 10;
    private final int ThuNo = 10; // Need Name

    // For TODO of sensor change
    private boolean isPlay = false; // Need Name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Component
        textView = (TextView) findViewById(R.id.text_details);

        // Sensor Configuration
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // liSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        liSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // MediaPlayer Init
        mp = new MediaPlayer();

    }

    private void playSound(){ // Need Name
        try {
            isPlay = true;
            // URi
            Uri uriPath = Uri.parse("android.resource://com.openlab.traiing/" + R.raw.s93232); // Method One of URI Select
            // Set Media
            mp.setDataSource(getApplicationContext(), uriPath);
            // mp.setDataSource(getApplicationContext(), Uri.parse("android.resource://com.openlab.traiing/" + R.raw.s93232));  // Method Two of URI Select
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0] > ThuNo &&  !isPlay){
            playSound();
        }
        // textView.setText("Sensor Details : " + String.valueOf(event.values[0])); // Need to change Text
        textView.setText(String.format("Sensor Details : %s", String.valueOf(event.values[0]))); // Need to change Text
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, liSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
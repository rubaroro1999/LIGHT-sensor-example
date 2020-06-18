package com.example.rubafikri.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor s;
    MediaPlayer mp;
    MediaPlayer mp1;
    ImageView im;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im  = findViewById(R.id.imageView2);
        mp = MediaPlayer.create(this, R.raw.dd);
        mp1 = MediaPlayer.create(this, R.raw.aa);


        sensorManager   = ( SensorManager ) getSystemService(this.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            s = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }else{
            Toast.makeText(this, "mmm", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_LIGHT){


           if (event.values[0] < 70){


               if (!mp.isPlaying()){
                   mp.start();

               }

               if (mp1.isPlaying()){
                   mp1.pause();
                   mp1.seekTo(1);

               }

               im.setImageResource(R.drawable.ee);


           }else {

               if (!mp1.isPlaying()){
                   mp1.start();

               }

               if (mp.isPlaying()){
                   mp.pause();
                   mp.seekTo(1);

               }

               im.setImageResource(R.drawable.ss);
           }
       }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}

package com.example.superstudent.Quotes;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.superstudent.R;


public class Home_Activity extends Fragment {

    QuoteRandom quoteRandom;
    SensorManager sensorManager;
    Sensor sensor;
    Shake shake;

    TextView tv_quote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

        View rootView = inflater.inflate(R.layout.activity_home, container, false);

        tv_quote = (TextView) container.findViewById(R.id.tv_quote);
        quoteRandom =new QuoteRandom();


       /*sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shake =new Shake(new Shake.OnShakeListener() {
            @Override
            public void onShake() {
                String quote= quoteRandom.getQuote();
                tv_quote.setText(quote);
            }
        });*/
        return rootView;
    }




   /*@Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(shake, sensor, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shake);

    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.home) {
            return true;
        } else if (id == R.id.profile) {
            intent = new Intent(Home_Activity.this, Profile_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.to_do_list) {
            intent = new Intent(Home_Activity.this, ToDo_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.materials) {
            intent = new Intent(Home_Activity.this, Materials_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.map) {
            intent = new Intent(Home_Activity.this, Map_Activity.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.chat) {
            intent = new Intent(Home_Activity.this, BTChat.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}

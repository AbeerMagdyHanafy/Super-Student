package com.example.superstudent.Quotes;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.superstudent.MainActivity;
import com.example.superstudent.R;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Home_Activity extends Fragment  {

    QuoteRandom quoteRandom;
    SensorManager sensorManager;
    Sensor sensor;
    Shake shake;

    TextView tv_quote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        //setContentView(R.layout.activity_home);

        View rootView = inflater.inflate(R.layout.activity_home, container, false);

        tv_quote = (TextView) rootView.findViewById(R.id.tv_quote);
        quoteRandom =new QuoteRandom();


       sensorManager = (SensorManager)getActivity().getSystemService(getActivity().SENSOR_SERVICE);;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shake =new Shake(new Shake.OnShakeListener() {
            @Override
            public void onShake() {
                String quote= quoteRandom.getQuote();
                tv_quote.setText(quote);
            }
        });
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(shake, sensor, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shake);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.logout_menu) {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}

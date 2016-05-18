package com.example.superstudent.Quotes;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.BTChat.BTChat;
import com.example.superstudent.Map.Map_Activity;
import com.example.superstudent.Materials.Materials_Activity;
import com.example.superstudent.Profile.Profile_Activity;
import com.example.superstudent.R;
import com.example.superstudent.ToDoList.ToDo_Activity;


public class Home_Activity extends AppCompatActivity {

    QuoteRandom quoteRandom;
    SensorManager sensorManager;
    Sensor sensor;
    Shake shake;

    TextView tv_quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar logo = getSupportActionBar();
        logo.setTitle("Home");

        tv_quote = (TextView) findViewById(R.id.tv_quote);
        quoteRandom =new QuoteRandom();


        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shake =new Shake(new Shake.OnShakeListener() {
            @Override
            public void onShake() {
                String quote= quoteRandom.getQuote();
                tv_quote.setText(quote);
            }
        });
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

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Home_Activity.this);
//            alertDialogBuilder
//                    .setMessage("Click yes to exit!")
//                    .setCancelable(false)
//                    .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            Home_Activity.this.finish();
//                            System.exit(0);
//                        }
//                    })
//                    .setPositiveButton("No", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.cancel();
//                        }
//                    }).show();
//        }
//        return true;
//    }

    @Override
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
    }
}

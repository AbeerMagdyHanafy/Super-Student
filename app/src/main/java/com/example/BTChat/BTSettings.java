package com.example.BTChat;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.superstudent.R;

public class BTSettings extends AppCompatActivity {

    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;
    private BluetoothAdapter mBluetoothAdapter = null;

    private ListView settingsView;
    private ArrayAdapter<String> settingsArrayAdapter;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btsettings);
        settingsView = (ListView) findViewById(R.id.list_view_settings);
        settingsArrayAdapter = new ArrayAdapter<String>(this.getApplicationContext(), R.layout.message);
        settingsView.setAdapter(settingsArrayAdapter);
        settingsArrayAdapter.add("Connect a device - Secure");
        settingsArrayAdapter.add("Connect a device - Insecure");
        settingsArrayAdapter.add("Make discoverable");

        settingsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = ((TextView) view).getText().toString();

                if(item.equals("Connect a device - Secure")){
                    // Launch the DeviceListActivity to see devices and do scan
                    Intent serverIntent = new Intent(BTSettings.this, DeviceList.class);
                    startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
                }
                else if(item.equals("Connect a device - Insecure")){
                    // Launch the DeviceListActivity to see devices and do scan
                    Intent serverIntent = new Intent(BTSettings.this, DeviceList.class);
                    startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
                }
                else if (item.equals("Make discoverable")) {
                    // Ensure this device is discoverable by others
                    ensureDiscoverable();
                }
                finish();
            }
        });

    }
/**
 * Makes this device discoverable.
 */
    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }
}

package com.example.BTChat;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superstudent.R;

public class BTChat extends Fragment {

        //private static final String TAG = "BluetoothChatFragment";

        // Intent request codes
        private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
        private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
        private static final int REQUEST_ENABLE_BT = 3;

        // Layout Views
        private ListView mConversationView;
        private EditText mOutEditText;
        private Button mSendButton;

        /**
         * Name of the connected device
         */
        private String mConnectedDeviceName = null;

        /**
         * Array adapter for the conversation thread
         */
        private ArrayAdapter<String> mConversationArrayAdapter;

        /**
         * String buffer for outgoing messages
         */
        private StringBuffer mOutStringBuffer;

        /**
         * Local Bluetooth adapter
         */
        private BluetoothAdapter mBluetoothAdapter = null;

        /**
         * Member object for the chat services
         */
        private BluetoothChatService mChatService = null;

        View rootView;
        Button btnDeviceList;
        TextView stateTxtView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            super.onCreateView(inflater,container,savedInstanceState);
            rootView = inflater.inflate(R.layout.activity_btchat, container, false);

            stateTxtView=(TextView) rootView.findViewById(R.id.state);
            btnDeviceList=(Button) rootView.findViewById(R.id.btn_device_list);
            mConversationView = (ListView) rootView.findViewById(R.id.in);
            mOutEditText = (EditText) rootView.findViewById(R.id.edit_text_out);
            mSendButton = (Button) rootView.findViewById(R.id.button_send);
            // Get local Bluetooth adapter
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            btnDeviceList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(BTChat.this.getActivity(), DeviceList.class);
                    startActivity(intent);
                }
            });


            // If the adapter is null, then Bluetooth is not supported
            if (mBluetoothAdapter == null) {
                Toast.makeText(this.getActivity(), "Bluetooth is not available", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
            return rootView;
        }




        @Override
        public void onStart() {
            super.onStart();
            // If BT is not on, request that it be enabled.
            // setupChat() will then be called during onActivityResult
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                // Otherwise, setup the chat session
            } else if (mChatService == null) {
                setupChat();
            }
            btnDeviceList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(BTChat.this.getActivity(), DeviceList.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (mChatService != null) {
                mChatService.stop();
            }
        }

        @Override
        public void onResume() {
            super.onResume();

            // Performing this check in onResume() covers the case in which BT was
            // not enabled during onStart(), so we were paused to enable it...
            // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
            if (mChatService != null) {
                // Only if the state is STATE_NONE, do we know that we haven't started already
                if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
                    // Start the Bluetooth chat services
                    mChatService.start();
                }
            }
            btnDeviceList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent serverIntent = new Intent(BTChat.this.getActivity(), DeviceList.class);
                    startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
                }
            });
        }

     /**
         * Set up the UI and background operations for chat.
         */
    private void setupChat() {

        // Initialize the array adapter for the conversation thread
        mConversationArrayAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.message);

        mConversationView.setAdapter(mConversationArrayAdapter);

        // Initialize the compose field with a listener for the return key
        mOutEditText.setOnEditorActionListener(mWriteListener);

        // Initialize the send button with a listener that for click events
        mSendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView textView = (TextView) rootView.findViewById(R.id.edit_text_out);
                String message = textView.getText().toString();
                sendMessage(message);

            }
        });

        // Initialize the BluetoothChatService to perform bluetooth connections
        mChatService = new BluetoothChatService(this.getActivity(), mHandler);

        // Initialize the buffer for outgoing messages
        mOutStringBuffer = new StringBuffer("");
    }

    /**
     * Makes this device discoverable.
     */
   /* private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }*/

    /**
     * Sends a message.
     *
     * @param message A string of text to send.
     */

    //send message and mac address to server if device is not connected
    private void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
            Toast.makeText(this.getActivity(), R.string.not_connected, Toast.LENGTH_SHORT).show();
            return;

        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            mChatService.write(send);

            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);
            mOutEditText.setText(mOutStringBuffer);
        }
    }

    /**
     * The action listener for the EditText widget, to listen for the return key
     */
    private TextView.OnEditorActionListener mWriteListener
            = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
            // If the action is a key-up event on the return key, send the message
            if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP) {
                String message = view.getText().toString();
                sendMessage(message);
            }
            return true;
        }
    };

    /**
     * Updates the status
     *
     * @param resId a string resource ID
     */
    private void setStatus(int resId) {

        stateTxtView.setText(resId);
    }

    /**
     * Updates the status
     *
     * @param subTitle status
     */
    private void setStatus(CharSequence subTitle) {

        stateTxtView.setText(subTitle);
    }

    /**
     * The Handler that gets information back from the BluetoothChatService
     */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            setStatus(getString(R.string.title_connected_to, mConnectedDeviceName));
                            mConversationArrayAdapter.clear();
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            setStatus(R.string.title_connecting);
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            setStatus(R.string.title_not_connected);
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    mConversationArrayAdapter.add("Me:  " + writeMessage);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + readMessage);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != this) {
                        Toast.makeText(getActivity(), "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != this) {
                        Toast.makeText(getActivity(), msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE_SECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, true);
                }
                break;
            case REQUEST_CONNECT_DEVICE_INSECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, false);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a chat session
                    setupChat();
                } else {
                    // User did not enable Bluetooth or an error occurred
                    Toast.makeText(this.getActivity(), R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();

                }
        }
    }

    /**
     * Establish connection with other divice
     *
     * @param data   An {@link Intent} with {@link DeviceList#EXTRA_DEVICE_ADDRESS} extra.
     * @param secure Socket Security type - Secure (true) , Insecure (false)
     */
    private void connectDevice(android.content.Intent data, boolean secure) {
        // Get the device MAC address
        String address = data.getExtras()
                .getString(DeviceList.EXTRA_DEVICE_ADDRESS);
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        mChatService.connect(device, secure);
    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.chat) {
            return true;
        } else if (id == R.id.profile) {
            intent = new Intent(BTChat.this, Profile_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.to_do_list) {
            intent = new Intent(BTChat.this, ToDo_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.materials) {
            intent = new Intent(BTChat.this, Materials_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.map) {
            intent = new Intent(BTChat.this, Map_Activity.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.home) {
            intent = new Intent(BTChat.this, Home_Activity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}


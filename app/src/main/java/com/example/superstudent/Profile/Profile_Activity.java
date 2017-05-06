package com.example.superstudent.Profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.superstudent.R;


public class Profile_Activity extends Fragment {

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);

        button= (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Hellooo",Toast.LENGTH_LONG).show();
            }
        });
        return rootView;

    }





   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.home) {
            intent = new Intent(getApplicationContext(), Home_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.profile) {
            return true;
        } else if (id == R.id.to_do_list) {
            intent = new Intent(getApplicationContext(), ToDo_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.materials) {
            intent = new Intent(getApplicationContext(), Materials_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.map) {
            intent = new Intent(getApplicationContext(), Map_Activity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.chat) {
            intent = new Intent(getApplicationContext(), BTChat.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}

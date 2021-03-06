package com.example.superstudent.Materials;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.superstudent.MainActivity;
import com.example.superstudent.R;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Materials_Activity extends Fragment {

    Button btn1,btn2,btn3,btn4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        // setContentView(R.layout.activity_materials);

        //ActionBar logo = getSupportActionBar();
        //logo.setTitle("Materials");


        View rootView = inflater.inflate(R.layout.activity_materials, container, false);
        btn1=(Button)rootView.findViewById(R.id.year1btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent done = new Intent(getContext(), Year1.class);
                startActivity(done);
            }
        });
        btn2=(Button)rootView.findViewById(R.id.year2btn);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent done = new Intent(getContext(), Year2.class);
                startActivity(done);
            }
        });
        btn3=(Button)rootView.findViewById(R.id.year3btn);
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent done = new Intent(getContext(), Year3.class);
                startActivity(done);
            }
        });
        btn4=(Button)rootView.findViewById(R.id.year4btn);
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent done = new Intent(getContext(), Year4.class);
                startActivity(done);
            }
        });
        return  rootView;

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
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_materials, menu);
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
            intent = new Intent(getApplicationContext(), Profile_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.to_do_list) {
            intent = new Intent(getApplicationContext(), ToDo_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.materials) {
            return true;
        } else if (id == R.id.map) {
            intent = new Intent(getApplicationContext(), Map_Activity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.chat) {
            intent = new Intent(getApplicationContext(), BTChat.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Choose(View view)
    {
        if(view.getId()==R.id.year1btn)
        {
            Intent done = new Intent(getContext(), Year1.class);
            startActivity(done);
        }
        if(view.getId()==R.id.year2btn)
        {
            Intent done = new Intent(getContext(), Year2.class);
            startActivity(done);
        }
        if(view.getId()==R.id.year3btn)
        {
            Intent done = new Intent(getContext(), Year3.class);
            startActivity(done);
        }
        if(view.getId()==R.id.year4btn)
        {
            Intent done = new Intent(getContext(), Year4.class);
            startActivity(done);
        }
    }*/


}

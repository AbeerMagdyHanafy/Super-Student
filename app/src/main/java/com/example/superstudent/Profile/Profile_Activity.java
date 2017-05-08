package com.example.superstudent.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superstudent.MainActivity;
import com.example.superstudent.R;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Profile_Activity extends Fragment {

    TextView usernameText;
    Button btn_logout;

    SharedPreferences sharedpreferences;
    boolean First=true;
    String FirstTime ="FirstTime";
    FirebaseAuth auth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);

        Context c = getActivity().getApplicationContext();

         //((AppCompatActivity) getActivity()).getSupportActionBar();
        auth=FirebaseAuth.getInstance();
         btn_logout = (Button) rootView.findViewById(R.id.btn_logout);
        //facebook
        ImageView ppimageView = (ImageView) rootView.findViewById(R.id.pp_imageView);
        Profile profile = Profile.getCurrentProfile();
        if(profile != null)
        {
            String userName = profile.getFirstName()+ " "+ profile.getMiddleName()+" "+profile.getLastName();
        //profile.getLinkUri();

        Picasso.with(c).load(profile.getProfilePictureUri(150,150)).fit().into(ppimageView);

        usernameText=(TextView) rootView.findViewById(R.id.txt_username_profile);
       usernameText.setText(userName);
        }



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedpreferences = getActivity().getSharedPreferences(FirstTime, Context.MODE_PRIVATE);

                sharedpreferences.getBoolean(FirstTime,true);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                First =true;
                editor.putBoolean(FirstTime, First);
                editor.commit();
                //logout from firebase and Facebook;
                auth.signOut();
                LoginManager.getInstance().logOut();
                Intent logout = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(logout);
            }
        });
        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

            inflater.inflate(R.menu.menu_logout, menu);

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

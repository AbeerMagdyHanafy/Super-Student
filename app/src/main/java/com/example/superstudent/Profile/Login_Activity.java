package com.example.superstudent.Profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superstudent.HomeMain;
import com.example.superstudent.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;


public class Login_Activity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener{

    EditText email_login, password_login;
    Button loginBtn;

    //facebook
    LoginButton fbLoginButton;
    CallbackManager callbackManager;

    FirebaseAuth auth;


    //Google
    SignInButton btn_signin_google;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;



    SharedPreferences sharedpreferences;
    boolean First=true;
    String FirstTime ="FirstTime";
    String personName = null;
    Uri personPhoto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null) {
            //done();
            auth.signOut();
            finish();
        }
        setContentView(R.layout.activity_login_);
        auth=FirebaseAuth.getInstance();
//        getSupportActionBar().setTitle("Login");


//        sharedpreferences = getSharedPreferences(FirstTime, Context.MODE_PRIVATE);
//
//        boolean first =sharedpreferences.getBoolean(FirstTime,true);
//
//        if(!first)
//        {
//            done();
//        }



        //normal login
        loginBtn=(Button) findViewById(R.id.btn_done_login);
        email_login = (EditText) findViewById(R.id.ed_email_login);
        password_login = (EditText) findViewById(R.id.ed_password_login);
        btn_signin_google = (SignInButton) findViewById(R.id.btn_login_google);


///Google
        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_login.getText().toString().trim();
                final String password = password_login.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        password_login.setError("password length is too short");
                                    } else {
                                        Toast.makeText(Login_Activity.this, "Authentication failed, check your email and password", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(Login_Activity.this, "Welcome Student", Toast.LENGTH_LONG).show();
                                    done();
                                    finish();
                                }
                            }
                        });
            }
        });

        //facebook login
        callbackManager = CallbackManager.Factory.create();
        fbLoginButton = (LoginButton) findViewById(R.id.login_button);
        fbLoginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                done();
            }

            @Override
            public void onCancel() {
                Toast.makeText(Login_Activity.this, "Facebook Login attempt is cancelled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(Login_Activity.this, "Error while trying to login with Facebook.", Toast.LENGTH_SHORT).show();
            }
        });


        btn_signin_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        //super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount acct = result.getSignInAccount();
            if(acct !=null) {
                personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                personPhoto = acct.getPhotoUrl();

                Toast.makeText(getApplicationContext(), personName + personPhoto, Toast.LENGTH_LONG).show();
                handleSignInResult(result);
            }
        }
    }

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
           // Toast.makeText(getApplicationContext(),"s",Toast.LENGTH_LONG).show();
            done();
        } else {
            Toast.makeText(getApplicationContext(),"no account",Toast.LENGTH_LONG).show();

        }
    }
    // [END handleSignInResult]

    public void done() {
        sharedpreferences = getSharedPreferences(FirstTime, Context.MODE_PRIVATE);

        sharedpreferences.getBoolean(FirstTime,true);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        First =false;
        editor.putBoolean(FirstTime, First);
        editor.commit();

            Intent done = new Intent(this, HomeMain.class);
            startActivity(done);

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(getApplicationContext(),"Error while trying to login with Google.",Toast.LENGTH_LONG).show();
    }
}

package com.example.superstudent.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.superstudent.HomeMain;
import com.example.superstudent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;


public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL  = "http://superstudent.netne.net/registeration.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";


    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;


    private Button signUpButton, loginButton;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth=FirebaseAuth.getInstance();

        editTextUsername = (EditText) findViewById(R.id.ed_username);
        editTextEmail = (EditText) findViewById(R.id.ed_email);
        editTextPassword = (EditText) findViewById(R.id.ed_password);
        //buttons
        loginButton=(Button) findViewById(R.id.btn_gotologin);
        loginButton.setOnClickListener(this);
        signUpButton = (Button) findViewById(R.id.btn_submit);
        signUpButton.setOnClickListener(this);
    }

    private void SignUpFirebase(){
        final String email= editTextEmail.getText().toString().trim();
        final String password=editTextPassword.getText().toString().trim();
        String userName=editTextUsername.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(getApplicationContext(), "Enter user name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter at least 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
//
        auth.fetchProvidersForEmail(email)//, password)
                .addOnCompleteListener(SignUp_Activity.this, new OnCompleteListener<ProviderQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                        //Toast.makeText(SignUp_Activity.this, "fetchProvidersForEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            if (task.getResult().getProviders().isEmpty()) {

                                auth.createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(SignUp_Activity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (!task.isSuccessful()) {
                                                    Toast.makeText(SignUp_Activity.this, "Authentication failed." + task.getException(),
                                                            Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(SignUp_Activity.this, "Welcome Student",
                                                            Toast.LENGTH_LONG).show();
                                                    doneHome();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(SignUp_Activity.this, "Email already exists, go to login page or sign up with a new email",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(SignUp_Activity.this, "Connection error or bad email format",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                });

        }


    /*private void SignUp () {

        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        //   final String type = editTextType.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                /*Param 1*///Request.Method.POST,
                /*Param 2*///URL,
                /*param 3*/  //new Response.Listener<String>() {
         /*   @Override
            public void onResponse(String response) {
                Toast.makeText(SignUp_Activity.this,response,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), HomeMain.class);
                startActivity(intent);
            }
        },
                /*Param 4*/ //new Response.ErrorListener() {
           /* @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUp_Activity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,username);
                params.put(KEY_EMAIL, email);
                params.put(KEY_PASSWORD,password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }*/
    @Override
    public void onClick(View v) {
        if(v == signUpButton){
            SignUpFirebase();
        }
        else if(v==loginButton){
            doneLogin();
        }
    }

    private void doneHome(){
        Intent logged = new Intent(this, HomeMain.class);
        startActivity(logged);
    }

    private void doneLogin(){
        Intent login = new Intent(this, Login_Activity.class);
        startActivity(login);
    }
}

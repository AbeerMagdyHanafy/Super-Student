package com.example.superstudent.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.superstudent.HomeMain;
import com.example.superstudent.R;

import java.util.HashMap;
import java.util.Map;


public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL  = "http://superstudent.netne.net/registeration.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";


    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextType;
    private EditText editTextPassword;


    private Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        editTextUsername = (EditText) findViewById(R.id.ed_username);
        editTextEmail = (EditText) findViewById(R.id.ed_email);
        editTextPassword = (EditText) findViewById(R.id.ed_password);


        signUpButton = (Button) findViewById(R.id.btn_submit);

        signUpButton.setOnClickListener(this);
    }

    private void SignUp () {

        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        //   final String type = editTextType.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                /*Param 1*/Request.Method.POST,
                /*Param 2*/URL,
                /*param 3*/  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SignUp_Activity.this,response,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), HomeMain.class);
                startActivity(intent);
            }
        },
                /*Param 4*/ new Response.ErrorListener() {
            @Override
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

    }
    @Override
    public void onClick(View v) {
        if(v == signUpButton){
            SignUp();
        }
    }
}

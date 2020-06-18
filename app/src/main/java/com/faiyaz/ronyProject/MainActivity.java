package com.faiyaz.ronyProject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText login_email_edittext, login_password_edittext;

    Button login,signup;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Sign in");

        //init
        login_email_edittext = findViewById(R.id.login_edittext);
        login_password_edittext = findViewById(R.id.password_edittext);
        login = (Button) findViewById(R.id.login_btn);
        signup = (Button) findViewById(R.id.signup_btn);


        //listener with buttons
        login.setOnClickListener(this);
        signup.setOnClickListener(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //this is next activity function
                nextActivity();

            }
        });
    }


    public void nextActivity()
    {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.login_btn:
                break;
            case R.id.signup_btn:

                Intent gosignup = new Intent(getApplicationContext(),SignUp.class);
                startActivity(gosignup);
                break;

        }

    }





}

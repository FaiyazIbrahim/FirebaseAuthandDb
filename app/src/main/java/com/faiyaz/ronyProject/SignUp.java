package com.faiyaz.ronyProject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUp extends AppCompatActivity implements View.OnClickListener {


    EditText signup_email_edittext, signup_password_edittext;

    Button login,signup;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.setTitle("Sign Up");

        //init
        signup_email_edittext = findViewById(R.id.signup_email);
        signup_password_edittext = findViewById(R.id.signup_password);
        signup = (Button) findViewById(R.id.btn_signup);

        progressBar = findViewById(R.id.SignUp_progressBar);

        //listener with buttons
        signup.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        //FirebaseUser currentUser = mAuth.getCurrentUser();



    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_signup:
                userRegister();

                break;
        }
    }

    private void userRegister() {

        String email = signup_email_edittext.getText().toString().trim();
        String password = signup_password_edittext.getText().toString().trim();


        progressBar.setVisibility(View.VISIBLE);

        //checking the validity of the email
        if(email.isEmpty())
        {
            signup_email_edittext.setError("Enter an email address");
            signup_email_edittext.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signup_email_edittext.setError("Enter a valid email address");
            signup_email_edittext.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            signup_password_edittext.setError("Enter a password");
            signup_password_edittext.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            signup_password_edittext.setError("password should be bigger then 6");
            signup_password_edittext.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information

                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                }else
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}

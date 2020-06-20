package com.faiyaz.ronyProject;

import android.content.Intent;
import android.os.Bundle;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText login_email_edittext, login_password_edittext;

    Button login,signup;

    private FirebaseAuth mAuth;


    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Sign in");

        mAuth = FirebaseAuth.getInstance();


        //init
        login_email_edittext = findViewById(R.id.login_edittext);
        login_password_edittext = findViewById(R.id.password_edittext);
        login = (Button) findViewById(R.id.login_btn);
        signup = (Button) findViewById(R.id.signup_btn);
        progressBar = findViewById(R.id.progressBarlogin);


        //listener with buttons
        login.setOnClickListener(this);
        signup.setOnClickListener(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });
    }




    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.login_btn:
                loginUser();
                break;
            case R.id.signup_btn:

                Intent gosignup = new Intent(getApplicationContext(),SignUp.class);
                startActivity(gosignup);
                break;

        }

    }

    private void loginUser() {



        String email = login_email_edittext.getText().toString().trim();
        String password = login_password_edittext.getText().toString().trim();


        progressBar.setVisibility(View.VISIBLE);

        //checking the validity of the email
        if(email.isEmpty())
        {
            login_email_edittext.setError("Enter an email address");
            login_email_edittext.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            login_email_edittext.setError("Enter a valid email address");
            login_email_edittext.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            login_password_edittext.setError("Enter a password");
            login_password_edittext.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            login_password_edittext.setError("password should be bigger then 6");
            login_password_edittext.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful())
                {
                    finish();
                    progressBar.setVisibility(View.INVISIBLE);
                    Intent goHome = new Intent(getApplicationContext(),Home.class);
                    goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goHome);
                }

                else
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Login unsuccessfull", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}

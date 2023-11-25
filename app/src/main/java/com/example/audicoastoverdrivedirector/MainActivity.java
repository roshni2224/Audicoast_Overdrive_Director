package com.example.audicoastoverdrivedirector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewForget;
    private EditText editTextEmail, editTextPasswod, editTextUserName;
    private Button buttonLogin, buttonSignup;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();


        editTextEmail = (EditText)findViewById(R.id.sign_in_email);
        editTextPasswod = (EditText)findViewById(R.id.sign_in_password);
        textViewForget = (TextView) findViewById(R.id.forget_password_login);
        progressBar = findViewById(R.id.progressBar);

        buttonLogin=(Button)findViewById(R.id.log_in);

        buttonSignup=(Button)findViewById(R.id.sign_up);

        buttonLogin.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);
        textViewForget.setOnClickListener(this);








    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_up:
                startActivity(new Intent(MainActivity.this,sign_up.class));
                break;
            case R.id.log_in:
                userLogin();
                break;

            case R.id.forget_password_login:
                startActivity(new Intent(MainActivity.this,forget_password.class));
                break;

        }
    }

    private void userLogin() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPasswod.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email!");
            editTextEmail.requestFocus();
            return;

        }
        if(password.isEmpty()){
            editTextPasswod.setError("Password is required");
            editTextPasswod.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPasswod.setError("Minimum password length should be 6 characters!");
            editTextPasswod.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(MainActivity.this,MainActivity2.class));
                    progressBar.setVisibility(View.GONE);


                }else
                {
                    Toast.makeText(MainActivity.this, "Failed to login !", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
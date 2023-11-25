package com.example.audicoastoverdrivedirector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends AppCompatActivity  implements View.OnClickListener{


    private FirebaseAuth mAuth;

    private Button registerUser;
    private EditText editTextEmail, editTextUsername, editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        //dRef.push().setValue("CSE 2.2");

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button)findViewById(R.id.btn_signup);
        registerUser.setOnClickListener(this);

        editTextEmail = (EditText)findViewById(R.id.sign_up_email);
        editTextUsername =(EditText)findViewById(R.id.sign_up_page_username);
        editTextPassword = (EditText)findViewById(R.id.sign_up_password);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);







    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_signup:
                registerUser();
                break;
        }

    }

    private void registerUser() {

        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(username.isEmpty()){
            editTextUsername.setError("Username is required");
            editTextUsername.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;

        }

        if(password.length()<6){
            editTextPassword.setError("Minimum password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;

        }

        //================================
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {

                       if(task.isSuccessful())
                       {
                           User user = new User(username,email);

                           FirebaseDatabase.getInstance().getReference("users")
                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                   .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){
                                       Toast.makeText(sign_up.this, "User has been registered successfully ", Toast.LENGTH_LONG).show();
                                       progressBar.setVisibility(View.GONE);

                                   }else{
                                       Toast.makeText(sign_up.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                       progressBar.setVisibility(View.GONE);
                                   }

                               }
                           });
                       }else {

                           Toast.makeText(sign_up.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                           progressBar.setVisibility(View.GONE);

                       }




                    }

                });



    }
}
package com.example.audicoastoverdrivedirector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_password extends AppCompatActivity {
    private EditText emailEditText;
    private Button resetPasswordButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        emailEditText = (EditText)findViewById(R.id.forget_page_email);
        resetPasswordButton = (Button)findViewById(R.id.forget_page_signup);

        mAuth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });


    }

    private void resetPassword(){

        String email = emailEditText.getText().toString().trim();

        if(email.isEmpty()){
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please provide valid email!");
            emailEditText.requestFocus();
            return;

        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull  Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(forget_password.this, "Check your email to reset your password !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(forget_password.this, "Try again! Something wrong happened!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
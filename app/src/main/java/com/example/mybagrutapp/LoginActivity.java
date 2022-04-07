package com.example.mybagrutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn, reginBtn;
    private EditText username, password;
    private TextView error;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edUserN);
        password = findViewById(R.id.edPass);
        loginBtn = findViewById(R.id.submitBtn);
        reginBtn = findViewById(R.id.reginBtn);
        error = findViewById(R.id.tvError);

        mAuth = FirebaseAuth.getInstance();

    }

    public void login(View view)
    {
        mAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(LoginActivity.this,UserActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            error.setText("Wrong username or password :(");
                        }
                    }
                });
    }

    public void reginster(View view)
    {

        /*mAuth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(LoginActivity.this, "reginster failed", Toast.LENGTH_SHORT).show();
                    }
                });*/

    }

}
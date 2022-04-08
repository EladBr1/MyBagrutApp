package com.example.mybagrutapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, password;
    private TextView error;
    private FirebaseAuth mAuth;
    private Dialog reginDialog;
    private Button login, reginster;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edUserN);
        password = findViewById(R.id.edPass);
        error = findViewById(R.id.tvError);
        login = findViewById(R.id.loginBtn);
        reginster = findViewById(R.id.rginBtn);
        login.setOnClickListener(this);
        reginster.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onClick(View view)
    {
        if (login == view)
        {
            if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty())
            {
                Toast.makeText(LoginActivity.this, "Username or password is not inserted", Toast.LENGTH_SHORT).show();
            }
            else
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
        }

        if (reginster == view)
        {
           openDialog();
        }
    }

    public void openDialog()
    {

        reginDialog = new Dialog(LoginActivity.this);
        reginDialog.setContentView(R.layout.reginster_dialog);
        EditText newUsername = reginDialog.findViewById(R.id.edNewUser),
                newPassword = reginDialog.findViewById(R.id.edNewPass),
                rePassword = reginDialog.findViewById(R.id.edRepeatPass),
                code = reginDialog.findViewById(R.id.edCode);
        TextView errorText = reginDialog.findViewById(R.id.tvFalsePass);
        Button singUp = reginDialog.findViewById(R.id.signUpBtn),
                submitCode = reginDialog.findViewById(R.id.submitCodeBtn);
        LinearLayout codeLayout = reginDialog.findViewById(R.id.lnCode);
        reginDialog.setCancelable(true);
        codeLayout.setVisibility(View.INVISIBLE);

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newPassword.getText().toString().equals(rePassword.getText().toString())) {
                    codeLayout.setVisibility(View.VISIBLE);
                    submitCode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (code.getText().toString().equals("20047723"))
                            {
                                mAuth.createUserWithEmailAndPassword(newUsername.getText().toString(), newPassword.getText().toString())
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    reginDialog.dismiss();
                                                    Toast.makeText(LoginActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                                } else
                                                    Toast.makeText(LoginActivity.this, "reginster failed", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                            else
                            {
                                reginDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "wrong code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                    errorText.setText("*the repeted password is dfferent");
            }

        });
        reginDialog.show();

    }

}
package com.example.mybagrutapp;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends OptionsMenuActivity {

    private EditText username, password; //the username and password bars
    private TextView error; //error message
    private FirebaseAuth mAuth;//firebase authentication
    private Dialog reginDialog;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        broadcastReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastReceiver();

        username = findViewById(R.id.edUserN);
        password = findViewById(R.id.edPass);
        error = findViewById(R.id.tvError);

        mAuth = FirebaseAuth.getInstance();//instance for the firebase authentication

    }

    @Override
    public void onStart() //on start of the app
    {
        super.onStart();
        // Check if user is signed in and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //open the user screen
            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
            startActivity(intent);
        }
    }

    //if the user wants to sign in...
    public void login(View view)
    {
        if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) //if empty
        {
            Toast.makeText(LoginActivity.this, "Username or password are not inserted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //signing in
            signingIn(username.getText().toString(), password.getText().toString());
        }
    }

    public void signingIn(String username, String password)
    {
        mAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) //if the password is corrected...
                        {
                            //open the user screen
                            Intent intent = new Intent(LoginActivity.this,UserActivity.class);
                            startActivity(intent);
                        }
                        else // if the details are wrong
                        {
                            error.setText("Wrong username or password :(");
                        }
                    }
                });
    }

    //if user wants to register
    public void register(View view)
    {
        //open register dialog
        reginDialog = new Dialog(LoginActivity.this);
        reginDialog.setContentView(R.layout.register_dialog);

        EditText newUsername = reginDialog.findViewById(R.id.edNewUser),//new username
                newPassword = reginDialog.findViewById(R.id.edNewPass),// new password
                rePassword = reginDialog.findViewById(R.id.edRepeatPass),//confirm pass
                code = reginDialog.findViewById(R.id.edCode);//secret code
        TextView errorText = reginDialog.findViewById(R.id.tvFalsePass);//error message
        Button singUp = reginDialog.findViewById(R.id.signUpBtn),
                submitCode = reginDialog.findViewById(R.id.submitCodeBtn);
        LinearLayout codeLayout = reginDialog.findViewById(R.id.lnCode);
        reginDialog.setCancelable(true);
        codeLayout.setVisibility(View.INVISIBLE);//code layout invisible

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newPassword.getText().toString().equals(rePassword.getText().toString()) && !newPassword.getText().toString().isEmpty() && !newUsername.getText().toString().isEmpty()) {
                    codeLayout.setVisibility(View.VISIBLE);//make the layout visible
                    submitCode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //for registration, the user have to know the secret code...
                            if (code.getText().toString().equals("12345678"))
                            {
                                Toast.makeText(LoginActivity.this, "Enter the code below", Toast.LENGTH_SHORT).show();
                                //register the user
                                registration(newUsername.getText().toString(),newPassword.getText().toString());
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
                    errorText.setText("*the repeated password is different");
            }

        });
        reginDialog.show();
    }

    //register the user
    public void registration(String username, String password)
    {
        mAuth.createUserWithEmailAndPassword(username, password)
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

    protected void registerNetworkBroadcastReceiver()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

}


package com.example.mybagrutapp;

import android.Manifest;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class ContactUsActivity extends OptionsMenuActivity
{

    TextView email;
    Dialog smsDialog, learnMoreDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        email = findViewById(R.id.tvEmail);

    }

    public void copyEmail(View view)
    {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("TextView", email.getText().toString());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(ContactUsActivity.this, "Email copied",Toast.LENGTH_SHORT).show();
    }


    public void learnMore(View view)
    {

    }

    public void contact(View view)
    {
        String phoneNum = "0558831751";
        openSmsDialog(phoneNum);
    }

    public void openSmsDialog(String phoneNum)
    {
        smsDialog = new Dialog(ContactUsActivity.this);
        smsDialog.setContentView(R.layout.sms_dialog);
        EditText orgBar = smsDialog.findViewById(R.id.edOrgName);
        Button sendBtn = smsDialog.findViewById(R.id.sendBtn);
        smsDialog.setCancelable(true);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view)
            {
                String text = "Hi, I want to have an account, our organization name is: " + orgBar.getText().toString()
                        + ". We want to have a Champions League Legends account.";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
                {
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                        sendSMS(text, phoneNum);
                    else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }

                smsDialog.dismiss();
            }
        });

        smsDialog.show();

    }

    public void sendSMS(String text, String pNum)
    {
        try {
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(pNum, null,text,null,null);
            Toast.makeText(ContactUsActivity.this, "Message is sent!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ContactUsActivity.this, "Faild to send message",Toast.LENGTH_SHORT).show();
        }
    }

}
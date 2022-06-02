package com.example.mybagrutapp;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactUsActivity extends OptionsMenuActivity
{

    TextView email; //my email
    Dialog smsDialog, learnMoreDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        email = findViewById(R.id.tvEmail);

    }

    //copy my email to clipboard
    public void copyEmail(View view)
    {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("TextView", email.getText().toString());
        clipboard.setPrimaryClip(clip);

        //toast that confirm that the email has been copied
        Toast.makeText(ContactUsActivity.this, "Email copied",Toast.LENGTH_SHORT).show();
    }

    //if the user wants to learn more...
    public void learnMore(View view)
    {
        //the dialog opens
        openLearnMoreDialog();
    }

    //if the user wants the make contact with me by sending a whatsapp message
    public void contact(View view)
    {
        //my phone number
        String phoneNum = "+972558831751";


        if( isAppInstalled("com.whatsapp") )//if whatsapp is installed
        {
            //the dialog is open with my phone number
            openSmsDialog(phoneNum);
        }

        else
        {
            //the whatsapp is not installed...
            Toast.makeText(ContactUsActivity.this, "whatsapp is not installed...",Toast.LENGTH_SHORT).show();
        }

    }

    //check if the app is installed
    public boolean isAppInstalled(String appCode)
    {

        PackageManager packageManager = getPackageManager();
        boolean is_installed;

        try {
            packageManager.getPackageInfo(appCode, packageManager.GET_ACTIVITIES);
            is_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            is_installed = false;
            e.printStackTrace();
        }
        return is_installed;

    }

    //the dialog to send me the message
    public void openSmsDialog(String phoneNum)
    {
        smsDialog = new Dialog(ContactUsActivity.this);
        smsDialog.setContentView(R.layout.sms_dialog);
        EditText orgBar = smsDialog.findViewById(R.id.edOrgName); //the organization that wants to have an account
        Button sendBtn = smsDialog.findViewById(R.id.sendBtn);
        smsDialog.setCancelable(true);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //the message
                String text = "Hi, I want to have an account, our organization name is: " + orgBar.getText().toString()
                        + ". We want to have a Champions League Legends account.";

                //open whatsapp with the message
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + phoneNum + "&text=" + text));
                startActivity(intent);

                smsDialog.dismiss();
            }
        });

        smsDialog.show();

    }

    //learn more dialog
    private void openLearnMoreDialog()
    {
        learnMoreDialog = new Dialog(ContactUsActivity.this);
        learnMoreDialog.setContentView(R.layout.ln_dialog);
        learnMoreDialog.setCancelable(true);
        Button dismiss = learnMoreDialog.findViewById(R.id.dismissBtn);

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                learnMoreDialog.dismiss();
            }
        });

        learnMoreDialog.show();
    }

}
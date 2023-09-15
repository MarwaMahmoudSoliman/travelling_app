package com.example.sec_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView tv,tv2;
Button sign;
Button logout,sendEmail,message;
   String name ,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sign=findViewById(R.id.signup_btn);
        sendEmail=findViewById(R.id.sendemail);
        message=findViewById(R.id.Message);
        preferences=getSharedPreferences("session",MODE_PRIVATE);
        logout=findViewById(R.id.logout);
        tv=findViewById(R.id.hint);
        tv2=findViewById(R.id.name);
         name=preferences.getString("name","");
         email=preferences.getString("email","Sign in to see deals and Genius discounts,manage your trips,and more");
        tv.setText(email);
        tv2.setText(name);
        if(name!=""){
        sign.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
        }
        else{
            sign.setVisibility(View.VISIBLE) ;
            logout.setVisibility(View.GONE);
        }
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor=preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(profile.this, profile.class);
                startActivity(intent);
                finish();
            }
        });
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "1234567890";

// Create the URI with the smsto scheme and recipient phone number
                String uriText = "smsto:" + Uri.encode(phoneNumber);

// Create the intent with the ACTION_SENDTO action and the URI
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(uriText));

// Check if there is a messaging app available to handle the intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Start the messaging app activity
                    startActivity(intent);
                } else {
                    // Handle the case when no messaging app is available
                    Toast.makeText(profile.this, "No messaging app found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void sendMail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+"engmohamedsw@gmail.com"));
      //  intent.putExtra(Intent.EXTRA_SUBJECT, "mo");
// Set the body of the email
        //intent.putExtra(Intent.EXTRA_TEXT, "rrrrr");
// Check if there is an email app available to handle the intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the email activity
            startActivity(intent);
        } else {
            // Handle the case when no email app is available
            Toast.makeText(this, "No email app found.", Toast.LENGTH_SHORT).show();
        }
    }
}


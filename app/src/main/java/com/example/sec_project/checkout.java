package com.example.sec_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class checkout extends AppCompatActivity {
    TextView cap,user,email,price,totalpeople;
    Button check;
    SharedPreferences preferences;
    String nme,eml;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        preferences=getSharedPreferences("session",MODE_PRIVATE);
        nme=preferences.getString("name","");
        eml=preferences.getString("email","");
        cap=findViewById(R.id.trip_name);
        user=findViewById(R.id.user_name);
        email=findViewById(R.id.user_email);
        price=findViewById(R.id.total_price);
        totalpeople=findViewById(R.id.total_people);
        check=findViewById(R.id.confirm);
        Intent intent=getIntent();
        String cap_=intent.getExtras().getString("name");
        int tol=  intent.getExtras().getInt("total");
        String pricee=   intent.getExtras().getString("price");
        int pric=  Integer.parseInt((pricee));
        int numofpeople=tol/pric;
        cap.setText(cap_);
        price.setText(Integer.toString(tol));
        totalpeople.setText(Integer.toString(numofpeople));
        user.setText(nme);
        email.setText(eml);


        check.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(checkout.this);
                    builder.setTitle("Confirmation");
                    builder.setMessage("Are you sure you want to proceed?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            database = FirebaseDatabase.getInstance();
                            reference = database.getReference("recepts");
                            recept Recept = new recept(tol, numofpeople, eml, nme,cap_);
                            reference.child(nme).setValue(Recept);
                            Toast.makeText(checkout.this, "booking successfull!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }





        });


    }



}
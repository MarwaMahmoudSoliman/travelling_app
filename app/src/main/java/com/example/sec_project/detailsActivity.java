package com.example.sec_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class detailsActivity extends AppCompatActivity {
ImageView img;
TextView cap,describe,count;
FloatingActionButton fab;
    CircleImageView Plus,Miuns;
    Button btn;
String POS,Pricee;
int re=0;
    SharedPreferences preferences;
String nme,eml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
         fab = findViewById(R.id.map);
        Intent intent=getIntent();
        preferences=getSharedPreferences("session",MODE_PRIVATE);
        nme=preferences.getString("name","");
        eml=preferences.getString("email","");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailsActivity.this, MapsActivity.class);
                intent.putExtra("pos",POS );
                startActivity(intent);
            }
        });
        count=findViewById(R.id.counter);
       Plus=findViewById(R.id.plus);
       Miuns=findViewById(R.id.minus);
        img=findViewById(R.id.Image);
        describe=findViewById(R.id.des_);
        cap=findViewById(R.id.caption);
        btn=findViewById(R.id.checkout);

        Pricee=intent.getExtras().getString("price");

        int result=  Integer.parseInt((Pricee));
        count.setText(Integer.toString(result));
        re=Integer.parseInt((Pricee));
        Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re+=result;
                count.setText(Integer.toString(re));
            }
        });
        Miuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(re>result){
                    re-=result;
                    count.setText(Integer.toString(re));
                }

            }
        });

String cap_=intent.getExtras().getString("caption");
String des=intent.getExtras().getString("des");
String img_=intent.getExtras().getString("image");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nme.equals("")&&!eml.equals("")){

                Intent intent = new Intent(detailsActivity.this, checkout.class);
                intent.putExtra("name", cap_);
                intent.putExtra("total", re);
                intent.putExtra("price", Pricee);
                startActivity(intent);}
                else{
                    showAlert();
                }

            }
        });
 POS=intent.getExtras().getString("pos");
        cap.setText(cap_);
        describe.setText(des);
        Glide.with(this).load(img_).into(img);
    }
    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert Dialog");
        builder.setMessage("please sign first.");
        // Set positive button and its click listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when OK button is clicked
                dialog.dismiss(); // Close the dialog
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


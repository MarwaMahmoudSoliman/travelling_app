package com.example.sec_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView rv;
    RecyclerView rv_items;
    ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rv = findViewById(R.id.rv_places);
        rv_items = findViewById(R.id.rv_items);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query query = database.getReference("Images").orderByChild("rating").startAt(4.0);
        ArrayList<TouristPlaces> places = new ArrayList<>();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<DataClass> places_ = new ArrayList<>();
                for (DataSnapshot placeSnapshot : dataSnapshot.getChildren()) {
                    DataClass place = placeSnapshot.getValue(DataClass.class);
                    places_.add(place);
                }
        places.add(new TouristPlaces(R.drawable.religious," Religious landmarks","Religious landmarks",3));
        places.add(new TouristPlaces(R.drawable.maincoastal,"coastal places","coastal places",3));
        places.add(new TouristPlaces(R.drawable.p,"Archaeological","Archaeological sites",3));
        places.add(new TouristPlaces(R.drawable.touristattraction,"tourist Attractions","tourist Attractions",3));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity2.this ,places_);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity2.this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        ItemsAdapter adapter_items = new ItemsAdapter(MainActivity2.this,places);
        RecyclerView.LayoutManager lm_items = new LinearLayoutManager(MainActivity2.this, LinearLayoutManager.HORIZONTAL, false);
        rv_items.setHasFixedSize(true);
        rv_items.setLayoutManager(lm_items);
        rv_items.setAdapter(adapter_items);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        imageSlider =findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.egyptian_museum,null));
        imageList.add(new SlideModel(R.drawable.touristattraction,"",null));
        imageList.add(new SlideModel(R.drawable.maincoastal,null));
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    return true;
                case R.id.bottom_search:
                    startActivity(new Intent(getApplicationContext(), search.class));
                    overridePendingTransition(R.anim.slider_in_right, R.anim.slide_out_left);

                    return true;
                case R.id.bottom_settings:
                    startActivity(new Intent(getApplicationContext(), favourite.class));
                    overridePendingTransition(R.anim.slider_in_right, R.anim.slide_out_left);
                    return true;
                case R.id.bottom_profile:
                    startActivity(new Intent(getApplicationContext(), profile.class));
                    overridePendingTransition(R.anim.slider_in_right, R.anim.slide_out_left);
                   // finish();
                    return true;
            }
            return false;
        });


    }
}


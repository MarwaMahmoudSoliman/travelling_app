package com.example.sec_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.TouristViewHolder> {

    ArrayList<TouristPlaces> touristPlaces;
    private Context context;

    public ItemsAdapter(Context context,ArrayList<TouristPlaces> touristPlaces) {
        this.touristPlaces = touristPlaces;
        this.context = context;

    }

    @NonNull
    @Override
    public TouristViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsm,null,false);
        TouristViewHolder viewHolder = new TouristViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull TouristViewHolder holder, int position) {

        TouristPlaces t = touristPlaces.get(position);
        holder.im_image.setImageResource(t.getImg());
        holder.tv_site.setText(t.getSite());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String site = holder.tv_site.getText().toString();
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("cat",site);
                context.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return touristPlaces.size();
    }
    class TouristViewHolder extends RecyclerView.ViewHolder{
        ImageView im_image;
        TextView tv_site;
        public TouristViewHolder(@NonNull View itemView) {
            super(itemView);
            im_image = itemView.findViewById(R.id.imageView);
            tv_site = itemView.findViewById(R.id.textView_site);
        }
    }

}


package com.example.sec_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TouristViewHolder> {
    ArrayList<DataClass> touristPlaces;
    private Context context;

    public RecyclerViewAdapter(Context context,ArrayList<DataClass> touristPlaces) {
        this.touristPlaces = touristPlaces;
        this.context = context;

    }
    @NonNull
    @Override
    public TouristViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_item,null,false);
        TouristViewHolder viewHolder = new TouristViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TouristViewHolder holder, int position) {
        DataClass t = touristPlaces.get(position);
        Glide.with(context).load(touristPlaces.get(position).getImageURL()).into(holder.im_image);

       // holder.im_image.setImageResource(t.getImg());
        holder.tv_name.setText(t.getCaption());
        holder.tv_site.setText(t.getPrice());
    }

    @Override
    public int getItemCount() {
        return touristPlaces.size();
    }

    class TouristViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        ImageView im_image;
        TextView tv_site;

        public TouristViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.textView_name);
            im_image = itemView.findViewById(R.id.imageView);
            tv_site = itemView.findViewById(R.id.textView_site);
        }
    }

}


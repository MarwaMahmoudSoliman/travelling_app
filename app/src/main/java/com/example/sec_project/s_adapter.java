package com.example.sec_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class s_adapter extends RecyclerView.Adapter<s_adapter.MyViewHolder> {
    private ArrayList<DataClass> dataList;
    private Context context;
    public s_adapter(Context context, ArrayList<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_s, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(dataList.get(position).getImageURL()).into(holder.im_image);
        holder.tv_name.setText(dataList.get(position).getCaption());
          holder.tv_site.setText(dataList.get(position).getPrice()+"$");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dataList.get(position).getDes(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, detailsActivity.class);
                intent.putExtra("caption",dataList.get(position).getCaption());
                intent.putExtra("image",dataList.get(position).getImageURL());
                intent.putExtra("des",dataList.get(position).getDes());
                intent.putExtra("catlog",dataList.get(position).getCatog());
                intent.putExtra("pos",dataList.get(position).getPos());
                intent.putExtra("price",dataList.get(position).getPrice());

                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView im_image;
        TextView tv_site,tv_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im_image = itemView.findViewById(R.id.imageView);
            tv_site = itemView.findViewById(R.id.textView_site);
            tv_name = itemView.findViewById(R.id.textView_name);


        }
    }
}
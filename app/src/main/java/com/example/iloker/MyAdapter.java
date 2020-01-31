package com.example.iloker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Loker> lokers;

    public MyAdapter(Context c, ArrayList<Loker> l){

        context = c;
        lokers = l;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.namaLoker.setText(lokers.get(position).getNamaLoker());
        holder.deskripsiLoker.setText(lokers.get(position).getDeskripsiLoker());
        Picasso.get().load(lokers.get(position).getImageUrl()).into(holder.previewLoker);
    }

    @Override
    public int getItemCount() {
        return lokers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView namaLoker, deskripsiLoker;
        ImageView previewLoker;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namaLoker = (TextView) itemView.findViewById(R.id.namaLoker);
            deskripsiLoker = (TextView) itemView.findViewById(R.id.deskripsiLoker);
            previewLoker = (ImageView) itemView.findViewById(R.id.previewLoker);
        }
    }



}
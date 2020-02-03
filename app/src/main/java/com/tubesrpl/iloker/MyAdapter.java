package com.tubesrpl.iloker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.namaLoker.setText(lokers.get(position).getNamaLoker());
        holder.deskripsiLoker.setText(lokers.get(position).getDeskripsiLoker());
        if (lokers.get(position).getImageUrl() != null) {
            Picasso.get().load(lokers.get(position).getImageUrl()).into(holder.previewLoker);
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailLowonganActivity.class);
                i.putExtra("namaLoker", lokers.get(position).getNamaLoker());
                i.putExtra("deskripsiLoker", lokers.get(position).getDeskripsiLoker());
                i.putExtra("gambarLoker", lokers.get(position).getImageUrl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lokers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView namaLoker, deskripsiLoker;
        ImageView previewLoker;
        RelativeLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //listener untuk menampilkan detail loker yang dipilih
            //itemView.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        Intent i = Intent(v.getContext(), DetailLowonganActivity.class);
            //        i.putExtra("namaLoker",lokers.get());
            //        v.getContext().startActivity(i);
            //    }
            //});

            namaLoker = (TextView) itemView.findViewById(R.id.namaLoker);
            deskripsiLoker = (TextView) itemView.findViewById(R.id.deskripsiLoker);
            previewLoker = (ImageView) itemView.findViewById(R.id.previewLoker);
            parentLayout = (RelativeLayout) itemView.findViewById(R.id.parentLayout);
        }
    }



}
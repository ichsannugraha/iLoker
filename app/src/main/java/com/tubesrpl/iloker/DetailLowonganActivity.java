package com.tubesrpl.iloker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailLowonganActivity extends AppCompatActivity {

    ImageView mGambarDetail;
    TextView mNamaDetail;
    TextView mDeskripsiDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lowongan);

        mGambarDetail = findViewById(R.id.detailGambarLoker);
        mNamaDetail = findViewById(R.id.detailNamaLoker);
        mDeskripsiDetail = findViewById(R.id.detailDeskripsiLoker);

        Intent i = getIntent();

        if(i.hasExtra("namaLoker") && i.hasExtra("deskripsiLoker") && i.hasExtra("gambarLoker")){
            String namaLoker = i.getStringExtra("namaLoker");
            String deskripsiLoker = i.getStringExtra("deskripsiLoker");
            String gambarLoker = i.getStringExtra("gambarLoker");

            mNamaDetail.setText(namaLoker);
            mDeskripsiDetail.setText(deskripsiLoker);
            Picasso.get().load(gambarLoker).into(mGambarDetail);
        }



    }
}

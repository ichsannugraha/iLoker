package com.example.iloker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iloker.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonMasuk;
    TextView mTextViewDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextUsername = (EditText) findViewById(R.id.editText);
        mTextPassword = (EditText) findViewById(R.id.editText2);
        mButtonMasuk = (Button) findViewById(R.id.button);
        mTextViewDaftar = (TextView) findViewById(R.id.textView5);
        mTextViewDaftar.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent daftarIntent = new Intent(MainActivity.this,DaftarActivity.class);
                startActivity(daftarIntent);
            }
        });


        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);
    }
}

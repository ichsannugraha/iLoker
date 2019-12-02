package com.example.iloker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DaftarActivity extends AppCompatActivity {
    EditText mTextNama;
    EditText mTextPassword;
    EditText mTextEmail;
    EditText mTextTanggalLahir;
    EditText mTextTempatLahir;
    EditText mTextAlamat;

    Button mButtonDaftar;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        mTextNama = (EditText) findViewById(R.id.editText3);
        mTextPassword = (EditText) findViewById(R.id.editText4);
        mTextEmail = (EditText) findViewById(R.id.editText5);
        mTextTanggalLahir = (EditText) findViewById(R.id.editText6);
        mTextTempatLahir = (EditText) findViewById(R.id.editText7);
        mTextAlamat = (EditText) findViewById(R.id.editText8);

        mButtonDaftar = (Button) findViewById(R.id.button2);
        mTextViewLogin = (TextView) findViewById(R.id.textView12);
        mTextViewLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent loginIntent = new Intent(DaftarActivity.this,MainActivity.class);
                startActivity(loginIntent);
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);
    }
}

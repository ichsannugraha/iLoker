package com.example.iloker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DaftarActivity extends AppCompatActivity {
    private EditText mNamaTxt;
    private EditText mEmailTxt;
    private EditText mPasswordTxt;
    private EditText mTanggalLahirTxt;
    private EditText mTempatLahirTxt;
    private EditText mAlamatTxt;
    private Button mDaftarBtn;
    private TextView mTextViewLogin;
    private FirebaseAuth mFireBaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        roundLogo();

        mNamaTxt = findViewById(R.id.editText3);
        mPasswordTxt = findViewById(R.id.editText4);
        mEmailTxt = findViewById(R.id.editText5);
        mTanggalLahirTxt = findViewById(R.id.editText6);
        mTempatLahirTxt = findViewById(R.id.editText7);
        mAlamatTxt = findViewById(R.id.editText8);
        mDaftarBtn = findViewById(R.id.button2);
        mFireBaseAuth = FirebaseAuth.getInstance();

        mDaftarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailTxt.getText().toString().trim();
                String password = mPasswordTxt.getText().toString().trim();
                String nama = mNamaTxt.getText().toString();
                String tglLahir = mTanggalLahirTxt.getText().toString();
                String tmptLahir = mTempatLahirTxt.getText().toString();
                String alamat = mAlamatTxt.getText().toString();

                if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(DaftarActivity.this, "Email dan Password Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    mPasswordTxt.setError("Masukkan Email Anda!");
                    mPasswordTxt.requestFocus();
                }
                else if(email.isEmpty()){
                    mEmailTxt.setError("Masukkan Email Anda!");
                    mEmailTxt.requestFocus();
                }
                else if(!(email.isEmpty() && password.isEmpty())){
                    mFireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(DaftarActivity.this, HomeActivity.class));
                            }
                            else {
                                Toast.makeText(DaftarActivity.this, "Gagal Melakukan Pendaftaran!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(DaftarActivity.this, "Terjadi Kesalahan, Silahkan Coba Kembali!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mTextViewLogin = (TextView) findViewById(R.id.textView12);
        mTextViewLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent loginIntent = new Intent(DaftarActivity.this,MainActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    public void roundLogo(){
        //membuat gambar logo iLoker menjadi lingkaran
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);
    }
}

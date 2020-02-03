package com.tubesrpl.iloker;

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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText mUsernameTxt;
    private EditText mPasswordTxt;
    private Button mMasukBtn;
    private TextView mDaftarTxtView;

    private FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roundLogo();

        mFireBaseAuth = FirebaseAuth.getInstance();

        mUsernameTxt = findViewById(R.id.usernameEditTxt);
        mPasswordTxt = findViewById(R.id.passwordEditTxt);
        mMasukBtn = findViewById(R.id.masukBtn);
        mDaftarTxtView = findViewById(R.id.daftarTxt);


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser = mFireBaseAuth.getCurrentUser();
                if(mFireBaseUser != null){
                    //Toast.makeText(MainActivity.this,"Anda Berhasil Login!",Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }
                else{
                    //Toast.makeText(MainActivity.this,"Selamat Datang",Toast.LENGTH_SHORT).show();
                }

            }
        };

        mMasukBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mUsernameTxt.getText().toString().trim();
                String password = mPasswordTxt.getText().toString().trim();

                if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Email dan Password Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    mPasswordTxt.setError("Masukkan Email Anda!");
                    mPasswordTxt.requestFocus();
                }
                else if(email.isEmpty()){
                    mUsernameTxt.setError("Masukkan Email Anda!");
                    mUsernameTxt.requestFocus();
                }
                else if(!(email.isEmpty() && password.isEmpty())){
                    mFireBaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
                                startActivity(homeIntent);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Login Gagal, Silahkan Coba Kembali!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this, "Terjadi Kesalahan, Silahkan Coba Kembali!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mDaftarTxtView.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent daftarIntent = new Intent(MainActivity.this,DaftarActivity.class);
                startActivity(daftarIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFireBaseAuth.addAuthStateListener(mAuthStateListener);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    public void roundLogo(){
        //membuat gambar logo iLoker menjadi lingkaran
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);
    }
}

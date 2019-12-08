package com.example.iloker;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText usernameTxt;
    EditText passwordTxt;
    Button mButtonMasuk;
    TextView mTextViewDaftar;
    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireBaseAuth = FirebaseAuth.getInstance();

        usernameTxt = findViewById(R.id.usernameEditTxt);
        passwordTxt = findViewById(R.id.passwordEditTxt);
        mButtonMasuk = findViewById(R.id.masukBtn);
        mTextViewDaftar = findViewById(R.id.daftarTxt);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser = mFireBaseAuth.getCurrentUser();
                if(mFireBaseUser != null){
                    Toast.makeText(MainActivity.this,"Anda Berhasil Login!",Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Silahkan Login!",Toast.LENGTH_SHORT).show();
                }

            }
        };

        mButtonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameTxt.getText().toString().trim();
                String password = passwordTxt.getText().toString().trim();

                if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Email dan Password Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    passwordTxt.setError("Masukkan Email Anda!");
                    passwordTxt.requestFocus();
                }
                else if(email.isEmpty()){
                    usernameTxt.setError("Masukkan Email Anda!");
                    usernameTxt.requestFocus();
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

    @Override
    protected void onStart() {
        super.onStart();
        mFireBaseAuth.addAuthStateListener(mAuthStateListener);
    }
}

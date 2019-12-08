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

public class DaftarActivity extends AppCompatActivity {
    FirebaseAuth mFireBaseAuth;
    EditText mTextNama;
    EditText emailTxt;
    EditText passwordTxt;
    EditText mTextTanggalLahir;
    EditText mTextTempatLahir;
    EditText mTextAlamat;

    Button daftarBtn;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        mFireBaseAuth = FirebaseAuth.getInstance();

        mTextNama = findViewById(R.id.editText3);
        passwordTxt = findViewById(R.id.editText4);
        emailTxt = findViewById(R.id.editText5);
        mTextTanggalLahir = findViewById(R.id.editText6);
        mTextTempatLahir = findViewById(R.id.editText7);
        mTextAlamat = findViewById(R.id.editText8);

        daftarBtn = findViewById(R.id.button2);
        daftarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTxt.getText().toString().trim();
                String password = passwordTxt.getText().toString().trim();

                if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(DaftarActivity.this, "Email dan Password Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    passwordTxt.setError("Masukkan Email Anda!");
                    passwordTxt.requestFocus();
                }
                else if(email.isEmpty()){
                    emailTxt.setError("Masukkan Email Anda!");
                    emailTxt.requestFocus();
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

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);
    }
}

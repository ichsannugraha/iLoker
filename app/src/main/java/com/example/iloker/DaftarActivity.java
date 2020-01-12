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
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DaftarActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    private EditText mNamaTxt;
    private EditText mPasswordTxt;
    private EditText mEmailTxt;
    private EditText mTanggalLahirTxt;
    private EditText mTempatLahirTxt;
    private EditText mAlamatTxt;
    private Button mDaftarBtn;
    private TextView mLoginTxtView;
    private String userID;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        roundLogo();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        mNamaTxt = findViewById(R.id.editText3);
        mPasswordTxt = findViewById(R.id.editText4);
        mEmailTxt = findViewById(R.id.editText5);
        mTanggalLahirTxt = findViewById(R.id.editText6);
        mTempatLahirTxt = findViewById(R.id.editText7);
        mAlamatTxt = findViewById(R.id.editText8);
        mDaftarBtn = findViewById(R.id.button2);
        mLoginTxtView = findViewById(R.id.textView12);


        mDaftarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nama = mNamaTxt.getText().toString();
                final String password = mPasswordTxt.getText().toString();
                final String email = mEmailTxt.getText().toString();
                final String tglLahir = mTanggalLahirTxt.getText().toString();
                final String tmptLahir = mTempatLahirTxt.getText().toString();
                final String alamat = mAlamatTxt.getText().toString();

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
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //addUser(nama,password,email,tglLahir,tmptLahir,alamat);
                                userID = mFirebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = mFirestore.collection("users").document(userID);
                                Map<String,Object> user = new HashMap<>();
                                user.put("nama", nama);
                                user.put("email", email);
                                user.put("tglLahir", tglLahir);
                                user.put("tmptLahir",tmptLahir);
                                user.put("alamat", alamat);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: "+ e.toString());
                                    }
                                });
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

        mLoginTxtView.setOnClickListener(new View.OnClickListener(){
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

    public void addUser(String nama, String password, String email, String tglLahir, String tmptLahir, String alamat) {
        userID = mFirebaseAuth.getCurrentUser().getUid();
        CollectionReference dbUsers = mFirestore.collection("users");

        User user = new User(
                nama,
                password,
                email,
                tglLahir,
                tmptLahir,
                alamat
        );

        dbUsers.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(DaftarActivity.this, "User Berhasil Ditambahkan!",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DaftarActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

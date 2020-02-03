package com.tubesrpl.iloker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileFragment extends Fragment {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore mFirestore;

    private String userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_profile, container, false);
        //ConstraintLayout rootView = (ConstraintLayout) inflater.inflate(R.layout.fragment_profile, container, false);
        View rootView = inflater.inflate(R.layout.fragment_profile,container,false);
        roundLogo(rootView);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        userID = mFirebaseAuth.getCurrentUser().getUid();

        final TextView namaProfilTxt = rootView.findViewById(R.id.namaProfilTxt);
        final TextView tglLahirProfilTxt = rootView.findViewById(R.id.tglLahirProfilTxt);
        final TextView tmptLahirProfilTxt = rootView.findViewById(R.id.tmptLahirProfilTxt);
        final TextView alamatProfilTxt = rootView.findViewById(R.id.alamatProfilTxt);
        final TextView emailProfilTxt = rootView.findViewById(R.id.emailProfilTxt);

        Button uploadLoker = rootView.findViewById(R.id.uploadLokerBtn);


        final DocumentReference documentReference = mFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                String namaProfil = documentSnapshot.getString("nama");
                String tglLahirProfil = documentSnapshot.getString("tglLahir");
                String tmptLahirProfil = documentSnapshot.getString("tmptLahir");
                String alamatProfil = documentSnapshot.getString("alamat");
                String emailProfil = documentSnapshot.getString("email");

                namaProfilTxt.setText(namaProfil);
                tglLahirProfilTxt.setText(tglLahirProfil);
                tmptLahirProfilTxt.setText(tmptLahirProfil);
                alamatProfilTxt.setText(alamatProfil);
                emailProfilTxt.setText(emailProfil);
            }
        });

        uploadLoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), UploadLowonganActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }

    public void roundLogo(View view){
        //membuat gambar logo iLoker menjadi lingkaran
        ImageView imageView = (ImageView) view.findViewById(R.id.mainProfilePicture);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blank_profile_picture);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);
    }
}

package com.tubesrpl.iloker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UploadLowonganActivity extends AppCompatActivity {
    private String userID;

    private TextView mNamaLokerTxt;
    private TextView mDeskripsiLokerTxt;

    private Button mUploadBtn;
    private Button mChooseFileBtn;
    private ImageView mPreviewLoker;

    Uri FilepathUri;
    private FirebaseAuth mFirebaseAuth;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_lowongan);

        mFirebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("images");
        databaseReference = FirebaseDatabase.getInstance().getReference("loker");

        userID = mFirebaseAuth.getCurrentUser().getUid();

        mNamaLokerTxt = findViewById(R.id.namaLokerTxt);
        mDeskripsiLokerTxt = findViewById(R.id.deskripsiLokerTxt);
        mUploadBtn = findViewById(R.id.uploadLokerBtn);
        mChooseFileBtn = findViewById(R.id.chooseFileBtn);
        mPreviewLoker = findViewById(R.id.previewLoker);
        progressDialog = new ProgressDialog(UploadLowonganActivity.this);


        mChooseFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });

        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaLoker = mNamaLokerTxt.getText().toString();
                String deskripsiLoker = mDeskripsiLokerTxt.getText().toString();

                if (namaLoker.isEmpty() && deskripsiLoker.isEmpty()) {
                    Toast.makeText(UploadLowonganActivity.this, "Data tidak boleh kosong!",Toast.LENGTH_SHORT).show();
                }
                else if (namaLoker.isEmpty()){
                    mNamaLokerTxt.setError("Masukkan Nama Loker!");
                    mNamaLokerTxt.requestFocus();
                }
                else if (deskripsiLoker.isEmpty()) {
                    mDeskripsiLokerTxt.setError("Masukkan Deskripsi Loker!");
                    mDeskripsiLokerTxt.requestFocus();
                }
                else if (!(namaLoker.isEmpty() && deskripsiLoker.isEmpty())) {
                    UploadLoker();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilepathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilepathUri);
                mPreviewLoker.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadLoker() {

        if (FilepathUri != null) {

            progressDialog.setTitle("Sedang mengupload Loker...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilepathUri));
            storageReference2.putFile(FilepathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            /*
                            String namaLoker = mNamaLokerTxt.getText().toString().trim();
                            String deskripsiLoker = mDeskripsiLokerTxt.getText().toString();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            Loker loker = new Loker(namaLoker, deskripsiLoker, userID, taskSnapshot.);
                            String ImageUploadId = databaseReference.push().getKey();
                            databaseReference.child(ImageUploadId).setValue(loker);
                            */

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            String namaLoker = mNamaLokerTxt.getText().toString().trim();
                            String deskripsiLoker = mDeskripsiLokerTxt.getText().toString();
                            progressDialog.dismiss();
                            Toast.makeText(UploadLowonganActivity.this, "Loker berhasil diupload!", Toast.LENGTH_SHORT).show();

                            Loker loker = new Loker(namaLoker, deskripsiLoker, downloadUrl.toString(), userID);
                            String imageUploadId = databaseReference.push().getKey();
                            databaseReference.child(imageUploadId).setValue(loker);
                        }
                    });
        }
        else {
            Toast.makeText(UploadLowonganActivity.this, "Harap masukkan gambar terlebih dahulu", Toast.LENGTH_LONG).show();
        }
    }
}

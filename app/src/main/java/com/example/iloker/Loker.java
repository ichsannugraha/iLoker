package com.example.iloker;

public class Loker {
    public String namaLoker;
    public String deskripsiLoker;
    public String idUploader;
    public String imageUrl;

    public Loker(String namaLoker, String deskripsiLoker, String idUploader, String imageUrl) {
        this.namaLoker = namaLoker;
        this.deskripsiLoker = deskripsiLoker;
        this.idUploader = idUploader;
        this.imageUrl = imageUrl;
    }

    public String getNamaLoker() {
        return namaLoker;
    }

    public void setNamaLoker(String namaLoker) {
        this.namaLoker = namaLoker;
    }

    public String getDeskripsiLoker() {
        return deskripsiLoker;
    }

    public void setDeskripsiLoker(String deskripsiLoker) {
        this.deskripsiLoker = deskripsiLoker;
    }

    public String getIdUploader() {
        return idUploader;
    }

    public void setIdUploader(String idUploader) {
        this.idUploader = idUploader;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

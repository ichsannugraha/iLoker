package com.example.iloker;

public class Loker {
    public String namaLoker;
    public String deskripsiLoker;
    public String idUser;
    public String imageUrl;

    public Loker(String namaLoker, String deskripsiLoker, String idUser, String imageUrl) {
        this.namaLoker = namaLoker;
        this.deskripsiLoker = deskripsiLoker;
        this.idUser = idUser;
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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

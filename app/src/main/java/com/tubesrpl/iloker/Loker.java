package com.tubesrpl.iloker;

public class Loker {
    private String namaLoker;
    private String deskripsiLoker;
    private String imageUrl;
    private String idUser;

    public Loker() {
    }

    public Loker(String namaLoker, String deskripsiLoker, String imageUrl, String idUser) {
        this.namaLoker = namaLoker;
        this.deskripsiLoker = deskripsiLoker;
        this.imageUrl = imageUrl;
        this.idUser = idUser;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}

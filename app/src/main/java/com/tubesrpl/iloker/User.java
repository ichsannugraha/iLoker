package com.tubesrpl.iloker;

public class User {
    private String nama, password, email, tglLahir, tmptLahir, alamat;


    public User(String nama, String password, String email, String tglLahir, String tmptLahir, String alamat) {
        this.nama = nama;
        this.password = password;
        this.email = email;
        this.tglLahir = tglLahir;
        this.tmptLahir = tmptLahir;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public String getTmptLahir() {
        return tmptLahir;
    }

    public String getAlamat() {
        return alamat;
    }
}

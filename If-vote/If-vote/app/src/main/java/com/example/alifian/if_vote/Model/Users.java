package com.example.alifian.if_vote.Model;

/**
 * Created by ALIFIAN on 11/04/2018.
 */

public class Users {
    private String email;
    private String nama;
    private String password;

    public Users() {
    }

    public Users(String email, String nama, String password) {
        this.email = email;
        this.nama = nama;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

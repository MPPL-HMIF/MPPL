package com.example.alifian.if_vote.Model;

/**
 * Created by ALIFIAN on 18/04/2018.
 */

public class Kandidat {
    private String ketua;
    private String wakil;
    private String image;
    public String nopas;
    private String video;

    public Kandidat() {
    }

    public Kandidat(String ketua, String wakil, String image, String nopas, String video) {
        this.ketua = ketua;
        this.wakil = wakil;
        this.image = image;
        this.nopas = nopas;
        this.video = video;
    }

    public String getKetua() {

        return ketua;
    }

    public void setKetua(String ketua) {

        this.ketua = ketua;
    }

    public String getWakil() {

        return wakil;
    }

    public void setWakil(String wakil) {

        this.wakil = wakil;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getNopas() {
        return nopas;
    }

    public void setNopas(String nopas) {
        this.nopas = nopas;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}

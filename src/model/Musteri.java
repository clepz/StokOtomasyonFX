package model;

import org.json.JSONException;
import org.json.JSONObject;

public class Musteri {
    private String ad;
    private String soyad;
    private String tc;
    private String adres;
    private String cinsiyet;
    private String telefon;
    private String evTelefon;
    private String musteriNotu;

    public String getAd() {
        return ad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEvTelefon() {
        return evTelefon;
    }

    public void setEvTelefon(String evTelefon) {
        this.evTelefon = evTelefon;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }


    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }


    public String getMusteriNotu() {
        return musteriNotu;
    }

    public void setMusteriNotu(String musteriNotu) {
        this.musteriNotu = musteriNotu;
    }

}

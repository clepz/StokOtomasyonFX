package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class KasiyerTablo {

    private String urun;
    private int miktar;
    private float fiyat;
    private float tutar;
    private String SeriNo;
    private Button sil;

    public KasiyerTablo() {

    }

    public KasiyerTablo(String urun, int miktar, float fiyat, float tutar, String seriNo) {
        this.urun = urun;
        this.miktar = miktar;
        this.fiyat = fiyat;
        this.tutar = tutar;
        SeriNo = seriNo;
        this.sil = new Button();
        sil.setText("sil");
        sil.setOnAction(event -> {

        });
    }

    public String getUrun() {
        return urun;
    }

    public void setUrun(String urunAdi) {
        this.urun = urunAdi;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public float getFiyat() {
        return fiyat;
    }

    public void setFiyat(float fiyat) {
        this.fiyat = fiyat;
    }

    public float getTutar() {
        return tutar;
    }

    public void setTutar(float tutar) {
        this.tutar = tutar;
    }

    public String getSeriNo() {
        return SeriNo;
    }

    public void setSeriNo(String seriNo) {
        SeriNo = seriNo;
    }

    public Button getSil() {
        return sil;
    }

    public void setSil(Button sil) {
        this.sil = sil;
    }
}

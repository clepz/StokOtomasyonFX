package model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Spinner;

public class SatisTablo extends RecursiveTreeObject<SatisTablo> {
    SimpleStringProperty barkod;
    SimpleStringProperty seriNo;
    SimpleStringProperty bolumNo;
    SimpleStringProperty fiyat;
    SimpleStringProperty urunAciklamasi;
    JFXButton silBtn;
    Integer miktar;

    public SatisTablo(String barkod, String seriNo, String bolumNo, String fiyat,
                      String urunAciklamasi) {
        this.barkod = new SimpleStringProperty(barkod);
        this.seriNo = new SimpleStringProperty(seriNo);
        this.bolumNo = new SimpleStringProperty(bolumNo);
        this.fiyat = new SimpleStringProperty(fiyat);
        this.urunAciklamasi = new SimpleStringProperty(urunAciklamasi);

    }

    public String getBarkod() {
        return barkod.get();
    }

    public SimpleStringProperty barkodProperty() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod.set(barkod);
    }

    public String getSeriNo() {
        return seriNo.get();
    }

    public SimpleStringProperty seriNoProperty() {
        return seriNo;
    }

    public void setSeriNo(String seriNo) {
        this.seriNo.set(seriNo);
    }

    public String getBolumNo() {
        return bolumNo.get();
    }

    public SimpleStringProperty bolumNoProperty() {
        return bolumNo;
    }

    public void setBolumNo(String bolumNo) {
        this.bolumNo.set(bolumNo);
    }

    public String getFiyat() {
        return fiyat.get();
    }

    public SimpleStringProperty fiyatProperty() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat.set(fiyat);
    }

    public String getUrunAciklamasi() {
        return urunAciklamasi.get();
    }

    public SimpleStringProperty urunAciklamasiProperty() {
        return urunAciklamasi;
    }

    public void setUrunAciklamasi(String urunAciklamasi) {
        this.urunAciklamasi.set(urunAciklamasi);
    }

    public JFXButton getSilBtn() {
        return silBtn;
    }

    public void setSilBtn(JFXButton silBtn) {
        this.silBtn = silBtn;
    }

    public Integer getMiktar() {
        return miktar;
    }

    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }
}

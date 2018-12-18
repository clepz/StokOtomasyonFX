package model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Spinner;

public class SatisTablo extends RecursiveTreeObject<SatisTablo> {
    private SimpleStringProperty barkod;
    private SimpleStringProperty seriNo;
    private SimpleStringProperty bolumNo;
    private SimpleFloatProperty fiyattablo;
    private SimpleStringProperty urunAciklamasi;
    private Spinner<Integer> miktarSpinner;
    private int miktar;
    private float fiyat;
    private int adet;

    public SatisTablo(String barkod, String seriNo, String bolumNo, float fiyat,
                      String urunAciklamasi, int adet) {
        this.barkod = new SimpleStringProperty(barkod);
        this.seriNo = new SimpleStringProperty(seriNo);
        this.bolumNo = new SimpleStringProperty(bolumNo);
        this.fiyattablo = new SimpleFloatProperty(fiyat);
        this.fiyat = fiyattablo.getValue();
        this.adet = adet;
        this.miktarSpinner = new Spinner<>(1,adet,1);
        this.miktar = miktarSpinner.getValue();
        this.urunAciklamasi = new SimpleStringProperty(urunAciklamasi);

        miktarSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            miktar = newValue;
            setFiyattablo(miktar*fiyat);
            System.out.println("update listner : fiyattablo-"+ this.fiyattablo + ", fiyat-"+ this.fiyat);
        });

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

    public String getUrunAciklamasi() {
        return urunAciklamasi.get();
    }

    public SimpleStringProperty urunAciklamasiProperty() {
        return urunAciklamasi;
    }

    public void setUrunAciklamasi(String urunAciklamasi) {
        this.urunAciklamasi.set(urunAciklamasi);
    }

    public float getFiyattablo() {
        return fiyattablo.get();
    }

    public SimpleFloatProperty fiyattabloProperty() {
        return fiyattablo;
    }

    public void setFiyattablo(float fiyattablo) {
        this.fiyattablo.set(fiyattablo);
    }

    public Spinner<Integer> getMiktarSpinner() {
        return miktarSpinner;
    }

    public void setMiktarSpinner(Spinner<Integer> miktarSpinner) {
        this.miktarSpinner = miktarSpinner;
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

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }
}

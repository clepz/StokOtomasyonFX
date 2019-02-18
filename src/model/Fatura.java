package model;

public class Fatura {

    private int faturaId;

    private String musteriTc;

    private float faturaTutari;

    private String faturaBilgisi;

    private int kullaniciId;

    private int adet;

    private int faturaNo;

    public int getFaturaId() {
        return faturaId;
    }

    public void setFaturaId(int faturaId) {
        this.faturaId = faturaId;
    }

    public String getMusteriTc() {
        return musteriTc;
    }

    public void setMusteriTc(String musteriTc) {
        this.musteriTc = musteriTc;
    }

    public float getFaturaTutari() {
        return faturaTutari;
    }

    public void setFaturaTutari(float faturaTutari) {
        this.faturaTutari = faturaTutari;
    }

    public String getFaturaBilgisi() {
        return faturaBilgisi;
    }

    public void setFaturaBilgisi(String faturaBilgisi) {
        this.faturaBilgisi = faturaBilgisi;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public int getFaturaNo() {
        return faturaNo;
    }

    public void setFaturaNo(int faturaNo) {
        this.faturaNo = faturaNo;
    }

    public Fatura() {
    }

    public int geKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public Fatura(int faturaId, String musteriTc, float faturaTutari, String faturaBilgisi, int kullaniciId, int adet, int faturaNo) {
        this.faturaId = faturaId;
        this.musteriTc = musteriTc;
        this.faturaTutari = faturaTutari;
        this.faturaBilgisi = faturaBilgisi;
        this.kullaniciId = kullaniciId;
        this.adet = adet;
        this.faturaNo = faturaNo;
    }
}

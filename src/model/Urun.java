package model;

public class Urun {

    private String barkod;

    private String marka;

    private String model;

    private String aciklama;

    private String seri_no;

    private float fiyat;

    private int bundleVarMi;

    private String bolum_no;

    public Urun(String barkod, String marka, String model, String aciklama, String seri_no, float fiyat, int bundleVarMi, String bolum_no) {
        this.barkod = barkod;
        this.marka = marka;
        this.model = model;
        this.aciklama = aciklama;
        this.seri_no = seri_no;
        this.fiyat = fiyat;
        this.bundleVarMi = bundleVarMi;
        this.bolum_no = bolum_no;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getSeri_no() {
        return seri_no;
    }

    public void setSeri_no(String seri_no) {
        this.seri_no = seri_no;
    }

    public float getFiyat() {
        return fiyat;
    }

    public void setFiyat(float fiyat) {
        this.fiyat = fiyat;
    }

    public int getBundleVarMi() {
        return bundleVarMi;
    }

    public void setBundleVarMi(int bundleVarMi) {
        this.bundleVarMi = bundleVarMi;
    }

    public String getBolum_no() {
        return bolum_no;
    }

    public void setBolum_no(String bolum_no) {
        this.bolum_no = bolum_no;
    }
}

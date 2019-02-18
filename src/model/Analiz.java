package model;

public class Analiz {
    private String kullaniciAdi;
    private double ciro;
    private String rol;

    public Analiz(String kullaniciAdi, double ciro, String rol) {
        this.kullaniciAdi = kullaniciAdi;
        this.ciro = ciro;
        this.rol = rol;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public double getCiro() {
        return ciro;
    }

    public void setCiro(double ciro) {
        this.ciro = ciro;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

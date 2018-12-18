package model;

import java.util.List;

public class KasiyerEkranıBilgiler {

    private Musteri musteri;
    private List<KasiyerTablo> kasiyerTablo;
    private List<Fatura> fatura;

    public KasiyerEkranıBilgiler(Musteri musteri, List<KasiyerTablo> kasiyerTablo, List<Fatura> fatura) {
        this.musteri = musteri;
        this.kasiyerTablo = kasiyerTablo;
        this.fatura = fatura;
    }

    public KasiyerEkranıBilgiler() {
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public List<KasiyerTablo> getKasiyerTablo() {
        return kasiyerTablo;
    }

    public void setKasiyerTablo(List<KasiyerTablo> kasiyerTablo) {
        this.kasiyerTablo = kasiyerTablo;
    }

    public List<Fatura> getFatura() {
        return fatura;
    }

    public void setFatura(List<Fatura> fatura) {
        this.fatura = fatura;
    }
}

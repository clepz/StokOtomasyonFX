package fxml;

import com.jfoenix.controls.JFXButton;
import http.HttpRequests;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Fatura;
import model.KasiyerEkranıBilgiler;
import model.KasiyerTablo;
import model.Musteri;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class KasiyerEkran {

    @FXML
    private Label lblFaturaTutar;

    @FXML
    private Label labelFaturaNo;

    @FXML
    private ComboBox<?> comboBoxMusteriListesi;

    @FXML
    private Label labelKullaniciId;

    @FXML
    private Label labelMusteriAdi;

    @FXML
    private Label labelMusteriTelefon;

    @FXML
    private Text labelAdres;

    @FXML
    private TableView<KasiyerTablo> tableViewTabloFatura;

    @FXML
    private TableColumn<?, ?> columnUrun;

    @FXML
    private TableColumn<?, ?> columnMiktar;

    @FXML
    private TableColumn<?, ?> columnFiyat;

    @FXML
    private TableColumn<?, ?> columnTutar;

    @FXML
    private TableColumn<?, ?> columnSeriNo;

    @FXML
    private TableColumn<KasiyerTablo, Button> columnSilBtn;

    @FXML
    private JFXButton btnSatisYap;

    ObservableList<KasiyerTablo> kasiyerTabloList;
    List<Musteri> musteri;
    Musteri secilenMusteri;

    @FXML
    void initialize(){
        kasiyerTabloList = FXCollections.observableArrayList();

        columnFiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
        columnMiktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));
        columnSeriNo.setCellValueFactory(new PropertyValueFactory<>("seriNo"));
        columnSilBtn.setCellValueFactory(new PropertyValueFactory<KasiyerTablo, Button>("sil"));
        columnTutar.setCellValueFactory(new PropertyValueFactory<>("tutar"));
        columnUrun.setCellValueFactory(new PropertyValueFactory<>("urun"));

        tableViewTabloFatura.setItems(kasiyerTabloList);
        HttpRequests requests = new HttpRequests();
        musteri = (List<Musteri>) requests.kasiyerMusteriAl();
        AtomicReference<KasiyerEkranıBilgiler> bilgiler = new AtomicReference<>();
        if(musteri != null){
            comboBoxMusteriListesi.getItems().addAll((Collection)musteri.stream().map(Musteri::getTc).collect(Collectors.toList()));
            comboBoxMusteriListesi.valueProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> {
                kasiyerTabloList.clear();
                tableViewTabloFatura.getItems().clear();
                bilgiler.set(requests.kasiyerEkranSatisBilgiAl(newValue.toString()));
                kasiyerTabloList.addAll(bilgiler.get().getKasiyerTablo());
                secilenMusteri = bilgiler.get().getMusteri();
                labelFaturaNo.setText(bilgiler.get().getFatura().get(0).getFaturaNo()+"");
                labelAdres.setText(bilgiler.get().getMusteri().getAdres());
                labelMusteriAdi.setText(bilgiler.get().getMusteri().getAd() + " " +bilgiler.get().getMusteri().getSoyad());
                labelMusteriTelefon.setText(bilgiler.get().getMusteri().getTelefon());
                labelKullaniciId.setText(bilgiler.get().getFatura().get(0).geKullaniciId() + "");
                float tutar = 0;
                for(Fatura a : bilgiler.get().getFatura()){
                    tutar += a.getFaturaTutari();
                }
                lblFaturaTutar.setText(tutar + " ₺");
            });
        }

    }

    @FXML
    void satisYapClicked(MouseEvent event) {
        HttpRequests requests = new HttpRequests();
        requests.kasiyerSatisOnay(secilenMusteri.getTc());
        comboBoxMusteriListesi.getItems().remove(secilenMusteri.getTc());
    }

}
package fxml;


import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import http.HttpRequests;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Urun;

import java.util.stream.Collectors;

public class UrunListeleEkran {
    @FXML
    private TextField textfieldBarkodListe;
    @FXML
    private ComboBox<String> comboBoxBolumNo;
    @FXML
    private TableView<Urun> tableViewTablo;
    @FXML
    private TableColumn<Urun, String> columnBarkod;
    @FXML
    private TableColumn<Urun, String> columnMarka;
    @FXML
    private TableColumn<Urun, String> columnModel;
    @FXML
    private TableColumn<Urun, String> columnAciklama;
    @FXML
    private TableColumn<Urun, String> columnSeriNo;
    @FXML
    private TableColumn<Urun, Float> columnFiyat;
    @FXML
    private TableColumn<Urun, Integer> columnBundle;
    @FXML
    private TableColumn<Urun, String> columnBolumNo;
    @FXML
    private TableColumn<?, ?> columnAdet;

    private ObservableList<Urun> urunler = FXCollections.observableArrayList();;

    @FXML
    void initialize(){

        HttpRequests requests = new HttpRequests();
        comboBoxBolumNo.getItems().addAll(requests.bolumNoAl());

        columnAciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
        columnBarkod.setCellValueFactory(new PropertyValueFactory<>("barkod"));
        columnBundle.setCellValueFactory(new PropertyValueFactory<>("bundleVarMi"));
        columnFiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
        columnMarka.setCellValueFactory(new PropertyValueFactory<>("marka"));
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnSeriNo.setCellValueFactory(new PropertyValueFactory<>("seri_no"));
        columnBolumNo.setCellValueFactory(new PropertyValueFactory<>("bolum_no"));
        columnAdet.setCellValueFactory(new PropertyValueFactory<>("adet"));

        urunler.addAll(requests.tumUrunleriAl());
        tableViewTablo.getItems().clear();
        tableViewTablo.getItems().addAll(urunler);

        comboBoxBolumNo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tableViewTablo.getItems().clear();
                tableViewTablo.getItems().addAll(urunler.stream().filter(u -> u.getBolum_no().equals(comboBoxBolumNo.getValue()))
                        .collect(Collectors.toList()));
            }
        });
    }

    @FXML
    void barkodKeyReleased(KeyEvent event) {
        if(textfieldBarkodListe.getText().length() == 16){
            tableViewTablo.getItems().clear();
            tableViewTablo.getItems().addAll(urunler.stream().filter(u -> u.getBarkod().equals(textfieldBarkodListe.getText()))
                    .collect(Collectors.toList()));
        }
        else if(textfieldBarkodListe.getText().length() == 0) {
            tableViewTablo.getItems().clear();
            tableViewTablo.getItems().addAll(urunler);
        }
    }

}
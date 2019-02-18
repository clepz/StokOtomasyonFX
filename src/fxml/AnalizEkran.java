package fxml;

import http.HttpRequests;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Analiz;

public class AnalizEkran {

    @FXML
    private TextField textFieldKullaniciAdi;

    @FXML
    private TableView<Analiz> tableAnalizTablo;

    @FXML
    private TableColumn<?, ?> columnKullaniciAdi;

    @FXML
    private TableColumn<?, ?> columnYetki;

    @FXML
    private TableColumn<?, ?> columnCiro;

    ObservableList<Analiz> liste;

    @FXML
    void initialize(){
        liste = FXCollections.observableArrayList();
        columnKullaniciAdi.setCellValueFactory(new PropertyValueFactory<>("kullaniciAdi"));
        columnCiro.setCellValueFactory(new PropertyValueFactory<>("ciro"));
        columnYetki.setCellValueFactory(new PropertyValueFactory<>("rol"));

        liste.addAll(new HttpRequests().analizHerkesAl());
        tableAnalizTablo.setItems(liste);
    }

}


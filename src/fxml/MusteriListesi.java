package fxml;

import com.jfoenix.controls.JFXButton;
import http.HttpRequests;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Musteri;

public class MusteriListesi {

    @FXML
    private AnchorPane anchorPaneMusteriListesi;

    @FXML
    private TextField textFieldTc;

    @FXML
    private JFXButton btnAra;

    @FXML
    private TableView<Musteri> tableViewTablo;

    @FXML
    private TableColumn<?, ?> columnTc;

    @FXML
    private TableColumn<?, ?> columnAd;

    @FXML
    private TableColumn<?, ?> columnSoyad;

    @FXML
    private TableColumn<?, ?> columnAdres;

    @FXML
    private TableColumn<?, ?> columnCinsiyet;

    @FXML
    private TableColumn<?, ?> columnTelefon;

    @FXML
    private TableColumn<?, ?> columnEvTelefon;

    ObservableList<Musteri> liste;


    @FXML
    void araClicked() {
        HttpRequests requests = new HttpRequests();
        liste.clear();
        liste.add(requests.musteriBilgisiniAl(textFieldTc.getText()));
    }

    @FXML
    void initialize(){
        liste = FXCollections.observableArrayList();

        columnAd.setCellValueFactory(new PropertyValueFactory<>("ad"));
        columnAdres.setCellValueFactory(new PropertyValueFactory<>("adres"));
        columnCinsiyet.setCellValueFactory(new PropertyValueFactory<>("cinsiyet"));
        columnEvTelefon.setCellValueFactory(new PropertyValueFactory<>("evTelefon"));
        columnSoyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        columnTc.setCellValueFactory(new PropertyValueFactory<>("tc"));
        columnTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));

        HttpRequests requests = new HttpRequests();
        liste.addAll(requests.musteriListeAl());

        tableViewTablo.setItems(liste);

    }

    @FXML
    void tcTextFieldReleased(KeyEvent event) {
        if(textFieldTc.getText().length() == 0){
            HttpRequests requests = new HttpRequests();
            liste.clear();
            liste.addAll(requests.musteriListeAl());
        }
        else{
            liste.clear();
        }
    }

}

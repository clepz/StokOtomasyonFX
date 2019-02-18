package fxml;

import com.jfoenix.controls.JFXButton;
import com.sun.deploy.util.FXLoader;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import loader.load;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AnaSayfaEkran {
    private Parent fxml;
    @FXML
    private JFXButton buttonSatis;
    @FXML
    private JFXButton buttonSatisAnaliz;

    @FXML
    private AnchorPane degisenAnchor;

    @FXML
    private JFXButton butonUrunListele;

    @FXML
    private JFXButton butonUrunEkle;

    @FXML
    private JFXButton butonKullaniciİslemleri;

    @FXML
    private JFXButton butonKasaIslemleri;

    @FXML
    private Button butonCikis;

    @FXML
    private AnchorPane anaSayfaAnchor;

    @FXML
    private FontAwesomeIconView butonKapat;

    @FXML
    private Label labelKullaniciAdi;

    @FXML

    void initialize (){
        if ((Login.user.getRol().equals("ROLE_ADMIN"))){
            butonKasaIslemleri.setDisable(true);
        }
        else if ((Login.user.getRol().equals("ROLE_USER"))){
            butonKasaIslemleri.setDisable(true);
            butonKullaniciİslemleri.setDisable(true);
            butonUrunEkle.setDisable(true);
        }
        else {
            butonUrunEkle.setDisable(true);
            butonKullaniciİslemleri.setDisable(true);
            butonUrunListele.setDisable(true);
            buttonSatisAnaliz.setDisable(true);


        }
        labelKullaniciAdi.setText(Login.user.getUsername());
    }

    @FXML
    void asaAlMouseClicked(MouseEvent event) {
        ((Stage)((FontAwesomeIconView)event.getSource()).getScene().getWindow()).setIconified(true);

    }

    @FXML
    void satisAction(ActionEvent event) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/fxml/anaEkran.fxml"));
            degisenAnchor.getChildren().removeAll();
            degisenAnchor.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void satisAnazlizAction(ActionEvent event) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/fxml/analizEkran.fxml"));
            degisenAnchor.getChildren().removeAll();
            degisenAnchor.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void urunListeleAction(ActionEvent event) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/fxml/urunListeleEkran.fxml"));
            degisenAnchor.getChildren().removeAll();
            degisenAnchor.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void urunEkleAction(ActionEvent event) {

        try {
            fxml= FXMLLoader.load(getClass().getResource("/fxml/urunEkle.fxml"));
            degisenAnchor.getChildren().removeAll();
            degisenAnchor.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void musteriListesiAction(ActionEvent event) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/fxml/musteriListesi.fxml"));
            degisenAnchor.getChildren().removeAll();
            degisenAnchor.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void kullaniciİslemleriAction(ActionEvent event) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/fxml/kullaniciIslemleri.fxml"));
            degisenAnchor.getChildren().removeAll();
            degisenAnchor.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void butonKasaIslemleriAction(ActionEvent event) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("/fxml/kasiyerEkran.fxml"));
            degisenAnchor.getChildren().removeAll();
            degisenAnchor.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void butonCikisAction(ActionEvent event) {
        Stage stage = (Stage)anaSayfaAnchor.getScene().getWindow();
        stage.close();
        load anaEkran = new load();
        try {
            anaEkran.loader("/fxml/login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @FXML
        void kapatMouseClicked(MouseEvent event) {

            System.exit(0);

        }

    }




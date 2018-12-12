package fxml;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

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
}

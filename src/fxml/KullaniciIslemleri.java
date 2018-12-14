package fxml;

import http.HttpRequests;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KullaniciIslemleri {

    @FXML
    private TextField textFieldKullaniciadi;

    @FXML
    private TextField textFieldSifre;

    @FXML
    private TextField textFieldKullaniciID;

    @FXML
    private ComboBox<?> comboBoxYetki;

    @FXML
    void initialize(){
        List<String> yetkiler = new ArrayList();
        yetkiler.add("Admin");
        yetkiler.add("Satıcı");
        yetkiler.add("Kasiyer");
        comboBoxYetki.getItems().addAll((Collection)yetkiler);
    }

    @FXML
    void ekleBtnAction(MouseEvent event) {

        HttpRequests requests = new HttpRequests();
        if(requests.kullaniciEkle(Integer.valueOf(textFieldKullaniciID.getText()),textFieldKullaniciadi.getText(),
                textFieldSifre.getText(),comboBoxYetki.getValue().toString()))
            System.out.println("oldu");
        else
            System.out.println("olmadi.");

    }

    @FXML
    void guncelleBtnAction(MouseEvent event) {

        HttpRequests requests = new HttpRequests();
        requests.kullaniGuncelle(Integer.valueOf(textFieldKullaniciID.getText()),textFieldSifre.getText(),comboBoxYetki.getValue().toString());

    }

    @FXML
    void silBtnAction(MouseEvent event) {

    }

}

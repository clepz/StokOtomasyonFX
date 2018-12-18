package fxml;

import http.HttpRequests;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private Label labelHataMesaj;

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
        {
            labelHataMesaj.setText("Ekleme Başarılı.");
        }
        else
            labelHataMesaj.setText("Ekleme Başarısız");
        labelHataMesaj.setVisible(true);

    }

    @FXML
    void guncelleBtnAction(MouseEvent event) {

        HttpRequests requests = new HttpRequests();
        try {
            if (requests.kullaniGuncelle(Integer.valueOf(textFieldKullaniciID.getText()), textFieldSifre.getText(), comboBoxYetki.getValue().toString()))
                labelHataMesaj.setText("Guncelleme Başarılı");
            else
                labelHataMesaj.setText("Güncelleme Başarısız. Bilgileri Kontrol Ediniz.");
        }catch (NullPointerException e){
            e.printStackTrace();
            labelHataMesaj.setText("Boşlukları Doldurunuz");
        }
        labelHataMesaj.setVisible(true);

    }

    @FXML
    void silBtnAction(MouseEvent event) {
        HttpRequests requests = new HttpRequests();
        if(requests.kullaniciSil(Integer.valueOf(textFieldKullaniciID.getText())))
            labelHataMesaj.setText("Silme Başarılı");
        else
            labelHataMesaj.setText("Silme Başarısız. Bilgileri Kontrol Ediniz.");
        labelHataMesaj.setVisible(true);
    }

}

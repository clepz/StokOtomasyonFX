package fxml;

import com.jfoenix.controls.JFXButton;
import http.HttpRequests;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Urun;

public class UrunEkleEkran {

        @FXML
        private TextField textfieldBarkod;

        @FXML
        private TextField textfieldMarka;

        @FXML
        private TextField textfieldModel;

        @FXML
        private TextField textfieldAciklama;

        @FXML
        private TextField textfieldSeriNo;

        @FXML
        private TextField textfieldFiyat;

        @FXML
        private TextField textfieldBolumNo;

        @FXML
        private TextField textfieldBundle;

        @FXML
        private TextField textFieldAdet;

        @FXML
        private TextField textFieldTutar;

        @FXML
        private JFXButton ButtonKaydet;

        @FXML
        void kaydetAction(ActionEvent event) {
            if(control() != true){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Boş Alanları Doldurunuz Ve Girdileri Kontrol Ediniz.", ButtonType.CLOSE);
                alert.setTitle("Uyarı");
                alert.show();
                return;
            }
            Urun urun = new Urun(textfieldBarkod.getText(),textfieldMarka.getText(),textfieldModel.getText(),textfieldAciklama.getText(),
                    textfieldSeriNo.getText(),Float.valueOf(textfieldFiyat.getText()),Integer.valueOf(textfieldBundle.getText()),textfieldBolumNo.getText()
                    ,Integer.valueOf(textFieldAdet.getText()));
            HttpRequests requests = new HttpRequests();
            requests.urunEkle(urun);
        }

    private boolean control() {
            if(textfieldBarkod.getText().length()<16 || textfieldBarkod.getText().length()>17)
                return false;
        // TODO: 16.12.2018 diğer kontroller eklenmeli.
        return true;
    }

    @FXML
    void adetKeyReleased(KeyEvent event) {
        try{
            textFieldTutar.setText(String.valueOf(Integer.valueOf(textFieldAdet.getText()) * Float.valueOf(textfieldFiyat.getText())));
        }catch (NumberFormatException e){
            textFieldTutar.setText(0+"");
        }
    }

    @FXML
    void fiyatKeyReleased(KeyEvent event) {
        try{
            textFieldTutar.setText(String.valueOf(Integer.valueOf(textFieldAdet.getText()) * Float.valueOf(textfieldFiyat.getText())));
        }catch (NumberFormatException e){
            textFieldTutar.setText(0+"");
        }
    }
}

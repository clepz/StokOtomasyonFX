package fxml;

import com.jfoenix.controls.JFXButton;
import http.HttpRequests;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Urun;

public class UrunEkleEkran {

    @FXML
    private Label geriMesajLabel;

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
            if(requests.urunEkle(urun)==1) {
                geriMesajLabel.setText("Ürün Ekleme/Güncelleme Başarılı");
                textFieldBosalt();
            }else
                geriMesajLabel.setText("Ürün Ekleme/Güncelleme Başarısız.");
            geriMesajLabel.setVisible(true);


        }

    private void textFieldBosalt() {
        textfieldModel.setText("");
        textfieldMarka.setText("");
        textfieldFiyat.setText("");
        textfieldAciklama.setText("");
        textFieldAdet.setText("");
        textfieldBolumNo.setText("");
        textfieldSeriNo.setText("");
        textfieldBundle.setText("");
        textfieldBarkod.setText("");
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

    @FXML
    void barkodKeyReleased(KeyEvent event) {
            Urun urun;
            HttpRequests requests = new HttpRequests();
        if(textfieldBarkod.getText().length()==16){
            urun = requests.urunAl(textfieldBarkod.getText());
            geriMesajLabel.setVisible(false);
            if(urun != null){
                textfieldModel.setText(urun.getModel());
                textfieldMarka.setText(urun.getMarka());
                textfieldFiyat.setText(String.valueOf(urun.getFiyat()));
                textfieldAciklama.setText(urun.getAciklama());
                textFieldAdet.setText(String.valueOf(urun.getAdet()));
                textfieldBolumNo.setText(urun.getBolum_no());
                textfieldSeriNo.setText(urun.getSeri_no());
                textfieldBundle.setText(String.valueOf(urun.getBundleVarMi()));
            }
        }
    }
}

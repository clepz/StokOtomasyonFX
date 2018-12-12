package fxml;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import http.HttpRequests;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import loader.load;
import model.User;

import java.io.IOException;

public class Login {

    public static User user;

    private double xOffset;
    private double yOffset;
   // private Stage stage;

    @FXML
    AnchorPane anaAnchor;

    @FXML
    Label mesaj;

    @FXML
    JFXTextField username;

    @FXML
    JFXPasswordField password;

    @FXML
    void anaEkranGirisClicked(MouseEvent event) {
        HttpRequests requests = new HttpRequests();
        user = requests.girisYap(username.getText().trim(),password.getText());
        if(user == null){
            mesaj.setText("Bağlantı Başarısız.");
            return;
        }else if(user.getRol().equals("-1")){
            mesaj.setText("Giriş bilgilerini kontrol ediniz.");
            return;
        }
        String adres;
        if(user.getRol().equals("ROLE_ADMIN"))
            adres = "/fxml/adminEkran.fxml";
        else if(user.getRol().equals("ROLE_USER"))
            adres = "/fxml/anaEkran.fxml";
        else if(user.getRol().equals("ROLE_KASIYER"))
            adres = "/fxml/kasiyerEkran.fxml";
        Stage stage = (Stage)anaAnchor.getScene().getWindow();
        stage.close();
        load anaEkran = new load();
        try {
            System.out.println(user.getRol());
            anaEkran.loader("/fxml/anaSayfaEkran.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
            xOffset = yOffset = 0;
            mesaj.setText("");
    }

    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) anaAnchor.getScene().getWindow();
        stage.setX(event.getScreenX()- xOffset);
        stage.setY(event.getScreenY()- yOffset);
    }

    @FXML
    void pressed(MouseEvent event) {
        xOffset=event.getSceneX();
        yOffset=event.getSceneY();
    }

    @FXML
    void closeClicked(){
        Platform.exit();
    }


}

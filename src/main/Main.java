package main;
import javafx.application.Application;
import javafx.stage.Stage;
import loader.load;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        load l=new load();

            l.loader("/fxml/login.fxml");


    }

    public static void main(String[] args) {
   launch(args);
        }


}

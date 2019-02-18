package loader;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class load {
    private double xOffset=0;
    private double yOffset=0;
    public void loader(String adres) throws IOException{

        Stage stage =new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(adres));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        root.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                xOffset=event.getSceneX();
                yOffset=event.getSceneY();

            }

        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                stage.setX(event.getScreenX()- xOffset);
                stage.setY(event.getScreenY()- yOffset);
            }
        });
    }


}

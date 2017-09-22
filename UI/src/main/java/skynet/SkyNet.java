/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skynet;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dariusz Lelek
 */
public class SkyNet extends Application {

  private final static String TITLE = "SkyNet UI";
  private final static String FXML = "fxml/SkyNetFXML.fxml";
  //private final static String IMAGE = "tracerfx/image.png";

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(TITLE);
    primaryStage.setScene(getScene());
    primaryStage.setMinWidth(700);
    primaryStage.setMinHeight(300);
    //primaryStage.getIcons().add(new Image(IMAGE));
    primaryStage.show();

    primaryStage.setOnCloseRequest(event -> {
       // TODO
    });
  }

  public static void main(String[] args) {
    launch(args);
  }

  private Parent getParent() throws IOException {
    return FXMLLoader.load(getClass().getClassLoader().getResource(FXML));
  }

  private Scene getScene() throws IOException {
    return new Scene(getParent());
  }
}

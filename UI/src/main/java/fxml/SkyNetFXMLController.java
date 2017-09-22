/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import core.Core;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import process.message.MessageCreator;
import process.message.MessageType;

/**
 * FXML Controller class
 *
 * @author Dariusz Lelek
 */
public class SkyNetFXMLController implements Initializable {

  private final MessageCreator messageCreator = new MessageCreator();

  @FXML
  private AnchorPane root;
  @FXML
  private TextField txtCommand;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  

  @FXML
  private void btnStart(ActionEvent event) {
    Core.start();
  }

  @FXML
  private void btnStop(ActionEvent event) {
    Core.stop();
  }

  @FXML
  private void btnExecuteCommand(ActionEvent event) {
    messageCreator.create(txtCommand.getText(), MessageType.UI);
    txtCommand.clear();
  }
  
}

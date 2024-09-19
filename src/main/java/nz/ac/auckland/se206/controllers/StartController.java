package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.App;

public class StartController {

  @FXML private Label startButton;

  @FXML
  public void initialize() {
  }

  @FXML
  private void onStart() throws IOException {
    App.openScene(startButton, "crimescene"); 
  }
}

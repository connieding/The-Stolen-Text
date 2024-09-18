package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;

public class StartController {

  @FXML private Button startButton;

  public void initialize() throws ApiProxyException {}

  @FXML
  private void onStart() throws IOException {
    App.openScene(startButton, "crimescene");
  }
}

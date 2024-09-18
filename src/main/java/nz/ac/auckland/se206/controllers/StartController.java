package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.App;

public class StartController {

  @FXML private Button startButton;

  @FXML
  private void onStart() throws IOException {
    App.openMainScreen(startButton);
  }
}

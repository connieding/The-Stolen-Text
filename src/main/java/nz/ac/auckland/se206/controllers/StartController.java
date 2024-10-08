package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;

public class StartController extends SoundController {

  @FXML private Label startButton;

  @FXML
  public void initialize() {
    preloadSound();
  }

  @FXML
  private void onStart() throws IOException {
    playSound();
    App.openScene(startButton, "crimescene");
  }

  @FXML
  void onExit(MouseEvent event) {
    playSound();
    App.handleWindowClose();
    Platform.exit();
  }
}

package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;

/**
 * This class is the controller for the start scene. It handles the start button and the exit
 * button.
 */
public class StartController extends SoundController {

  @FXML private Label startButton;

  @FXML
  public void initialize() {
    preloadSound();
  }

  /**
   * Handle the start button click and open the crimescene scene.
   *
   * @throws IOException if the scene is not found
   */
  @FXML
  private void onStart() throws IOException {
    playSound();
    App.openScene(startButton, "crimescene");
    App.overlayIntro();
  }

  private void onMouseEnterStart() {
    startButton.setOpacity(0.7);
  }

  private void onMouseExitStart() {
    startButton.setOpacity(1);
  }

  private void onMouseEnterExit() {
    startButton.setOpacity(0.7);
  }

  private void onMouseExitExit() {
    startButton.setOpacity(1);
  }

  /**
   * Handle the exit button click and close the game.
   *
   * @param event the mouse event
   */
  @FXML
  void onExit(MouseEvent event) {
    playSound();
    App.handleWindowClose();
    Platform.exit();
  }
}

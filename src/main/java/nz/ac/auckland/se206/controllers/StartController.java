package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;

/**
 * This class is the controller for the start scene. It handles the start button and the exit
 * button.
 */
public class StartController extends SoundController {

  @FXML private ImageView startButtonImage;
  @FXML private ImageView exitButtonImage;

  private Image defaultImageStart;
  private Image hoverImageStart;
  private Image defaultImageExit;
  private Image hoverImageExit;


  @FXML
  public void initialize() {
    preloadSound();
    defaultImageStart = new Image(getClass().getResourceAsStream("/images/startBut.png"));
    hoverImageStart = new Image(getClass().getResourceAsStream("/images/startButGlow.png"));
    defaultImageExit = new Image(getClass().getResourceAsStream("/images/exitBut.png"));
    hoverImageExit = new Image(getClass().getResourceAsStream("/images/exitButGlow.png"));
  }

  @FXML
  private void handleMouseEnterStart() {
    startButtonImage.setImage(hoverImageStart);
  }

  @FXML
  private void handleMouseExitStart() {
    startButtonImage.setImage(defaultImageStart);
  }

  @FXML
  private void handleMouseEnterExit() {
    exitButtonImage.setImage(hoverImageExit);
  }

  @FXML
  private void handleMouseExitExit() {
    exitButtonImage.setImage(defaultImageExit);
  }

  /**
   * Handle the start button click and open the crimescene scene.
   *
   * @throws IOException if the scene is not found
   */
  @FXML
  private void onStart() throws IOException {
    playSound();
    App.openScene(startButtonImage, "crimescene");
    App.overlayIntro();
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

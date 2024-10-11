package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

  /** Initializes the start view. */
  @FXML
  public void initialize() {
    preloadSound();
    defaultImageStart = new Image(getClass().getResourceAsStream("/images/startBut.png"));
    hoverImageStart = new Image(getClass().getResourceAsStream("/images/startButGlow.png"));
    defaultImageExit = new Image(getClass().getResourceAsStream("/images/exitBut.png"));
    hoverImageExit = new Image(getClass().getResourceAsStream("/images/exitButGlow.png"));
  }

  /** Handle the start button hover to change image. */
  @FXML
  private void handleMouseEnterStart() {
    startButtonImage.setImage(hoverImageStart);
  }

  /** Handle the start button hover to change image. */
  @FXML
  private void handleMouseExitStart() {
    startButtonImage.setImage(defaultImageStart);
  }

  /** Handle the exit button hover to change image. */
  @FXML
  private void handleMouseEnterExit() {
    exitButtonImage.setImage(hoverImageExit);
  }

  /** Handle the exit button hover to change image. */
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

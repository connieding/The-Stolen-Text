package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import nz.ac.auckland.se206.App;

/** This class is the controller for the intro scene. It handles the start image. */
public class IntroController extends Controller {

  @FXML private ImageView startImage;

  /** Initialize the intro controller of the game. */
  public void initialize() {
    // Any required initialization code can be placed here
  }

  /**
   * Handle rectangle click and hide the overlay.
   *
   * @param event the node event
   * @throws IOException if the overlay is not found
   */
  @FXML
  public void handleRectangleClicked(MouseEvent event) throws IOException {
    SoundController.playSound();

    AnchorPane overlay = (AnchorPane) startImage.getParent().getParent();
    overlay.setVisible(false);
    overlay.setDisable(true);

    App.hideOverlay();
  }

  @FXML
  private void handleMouseEnterStarted() {
    startImage.setOpacity(0.7);
  }

  @FXML
  private void handleMouseExitStarted() {
    startImage.setOpacity(1);
  }
}

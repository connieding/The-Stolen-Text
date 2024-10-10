package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;

public class IntroController extends Controller {

  @FXML private ImageView startImage;

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

    startImage.getParent().getParent().setVisible(false);
    startImage.getParent().getParent().setDisable(true);

    App.hideOverlay();
  }
}

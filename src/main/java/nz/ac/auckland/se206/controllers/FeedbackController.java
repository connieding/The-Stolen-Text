package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import nz.ac.auckland.se206.App;

/**
 * This class is the controller for the feedback scene. It handles the feedback message and the
 * retry and exit buttons.
 */
public class FeedbackController {

  @FXML private Text feedbackText;

  /**
   * Set the feedback message.
   *
   * @param feedback the feedback message
   */
  public void setFeedback(String feedback) {
    feedbackText.setText(feedback);
  }

  /**
   * Handle the retry button click and reset the game.
   *
   * @param event
   * @throws IOException
   */
  public void handleRetryClicked(MouseEvent event) throws IOException {
    App.reset();
    App.openScene((ImageView) event.getSource(), "crimescene");
  }

  /**
   * Handle the exit button click and close the game.
   *
   * @param event
   */
  public void handleExitClicked(MouseEvent event) {
    App.handleWindowClose();
    Platform.exit();
  }
}

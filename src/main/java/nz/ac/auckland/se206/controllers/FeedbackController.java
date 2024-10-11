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
  @FXML private ImageView retryImage; 
  @FXML private ImageView exitImage;

  /**
   * Set the feedback message to display to the player.
   *
   * @param feedback the feedback message
   */
  public void setFeedback(String feedback) {
    feedbackText.setText(feedback);
  }

  /**
   * Handle the retry button click and reset the game.
   *
   * @param event the mouse event
   * @throws IOException if the scene is not found
   */
  public void handleRetryClicked(MouseEvent event) throws IOException {
    SoundController.playSound();
    App.openScene((ImageView) event.getSource(), "crimescene");
  }

  /**
   * Handle the retry button mouse enter and lower opacity.
   */
  @FXML
  public void handleMouseEnterRetry() {
    retryImage.setOpacity(0.7);
  }

  /**
   * Handle the retry button mouse exit and raise opacity.
   */
  @FXML
  public void handleMouseExitRetry() {
    retryImage.setOpacity(1);
  }
  
  /**
   * Handle the exit button mouse enter and lower opacity.
   */
  @FXML
  public void handleMouseEnterExitGame() {
    exitImage.setOpacity(0.7);
  }

  /**
   * Handle the exit button mouse exit and raise opacity.
   */
  @FXML
  public void handleMouseExitExitGame() {
    exitImage.setOpacity(1);
  }

  /**
   * Handle the exit button click and close the game.
   *
   * @param event the mouse event
   */
  public void handleExitClicked(MouseEvent event) {
    SoundController.playSound();
    App.handleWindowClose();
    Platform.exit();
  }
}

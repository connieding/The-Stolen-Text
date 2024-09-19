package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import nz.ac.auckland.se206.App;

public class FeedbackController {

  @FXML private Text feedbackText;

  // Method to set the feedback message
  public void setFeedback(String feedback) {
    feedbackText.setText(feedback);
  }

  public void handleRetryClicked(MouseEvent event) throws IOException {
    App.reset();
    App.openScene((ImageView) event.getSource(), "crimescene");
  }

  public void handleExitClicked(MouseEvent event) {
    App.handleWindowClose();
    Platform.exit();
  }
}

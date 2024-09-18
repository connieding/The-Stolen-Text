package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class FeedbackController {

  @FXML private TextArea feedbackText;

  // Method to set the feedback message
  public void setFeedback(String feedback) {
    feedbackText.setText(feedback);
  }
}

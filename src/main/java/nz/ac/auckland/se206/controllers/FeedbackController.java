package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class FeedbackController {

  @FXML private Text feedbackText;

  // Method to set the feedback message
  public void setFeedback(String feedback) {
    feedbackText.setText(feedback);
  }
}

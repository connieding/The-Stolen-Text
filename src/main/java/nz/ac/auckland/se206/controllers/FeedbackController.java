package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;

public class FeedbackController {

  @FXML private TextArea feedbackText;

  // Method to set the feedback message
  public void setFeedback(String feedback) {
    feedbackText.setText(feedback);
  }
}

package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;

public class GuessController {

  @FXML private Label lblTimer;

  public void initialize() throws ApiProxyException {}

  public void setTime(String timeRemaining) {
    lblTimer.setText(timeRemaining);
  }

  public void handleSubmitClicked() {}
}

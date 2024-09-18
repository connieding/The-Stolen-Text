package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;

public interface Controller {

  void initialize() throws ApiProxyException;

  void setTime(String timeRemaining);

  void handleGuessClicked(MouseEvent event) throws IOException;

  void handleMapClicked(MouseEvent event) throws IOException;
}

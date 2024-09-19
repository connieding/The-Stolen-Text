package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameData;

public abstract class Controller {

  @FXML protected Label lblTimer;
  @FXML protected Rectangle buttonAccuse;
  @FXML protected AnchorPane mapSubScene;
  @FXML protected Rectangle buttonMap;

  protected boolean isMapOut = false;

  public abstract void initialize() throws ApiProxyException;

  public void setTime(String timeRemaining) {
    lblTimer.setText(timeRemaining);
  }

  public void handleGuessClicked(MouseEvent event) throws IOException {
    if (GameData.hasUsedClue()
        & GameData.hasMetSuspect("archivist")
        & GameData.hasMetSuspect("collector")
        & GameData.hasMetSuspect("historian")) {
      App.openGuessScene(buttonAccuse);
    }
  }

  public void handleMapClicked(MouseEvent event) throws IOException {

    // Toggle the map visibility/interaction
    mapSubScene.setVisible(!mapSubScene.isVisible());
    mapSubScene.setDisable(!mapSubScene.isDisable());

    // Toggle the map overlay
    if (!isMapOut) {
      App.overlayMap(buttonMap);
    } else {
      App.hideMap(buttonMap);
    }
    isMapOut = !isMapOut;
  }
}

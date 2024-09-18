package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;

public class CrimesceneController implements Controller {

  @FXML private AnchorPane crimescenePane;
  @FXML private ImageView crimesceneBg;
  @FXML private Rectangle buttonMap;
  @FXML private Rectangle clueOne;
  @FXML private Rectangle clueTwo;
  @FXML private Rectangle clueThree;
  @FXML private Text crimesceneHead;
  @FXML private Rectangle buttonAccuse;
  @FXML private Label lblTimer;
  @FXML private AnchorPane mapSubScene;

  private boolean isMapOut = false;

  @Override
  public void initialize() throws ApiProxyException {}

  @Override
  public void setTime(String timeRemaining) {
    lblTimer.setText(timeRemaining);
  }

  @Override
  public void handleGuessClicked(MouseEvent event) throws IOException {
    App.openGuessScene(buttonAccuse);
  }

  @Override
  public void handleMapClicked(MouseEvent event) throws IOException {

    mapSubScene.setVisible(!mapSubScene.isVisible());
    mapSubScene.setDisable(!mapSubScene.isDisable());
    if (!isMapOut) {
      App.overlayMap(buttonMap);
    } else {
      App.hideMap(buttonMap);
    }
    isMapOut = !isMapOut;
  }

  public void handleRectangleClicked(MouseEvent event) {
    System.out.println("Clue clicked: " + event.getSource());
  }
}

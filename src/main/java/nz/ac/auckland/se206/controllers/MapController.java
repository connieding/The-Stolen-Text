package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;

public class MapController {

  @FXML public AnchorPane mapPane;
  @FXML public ImageView mapBg;
  @FXML public Text mapHead;
  @FXML public Rectangle suspectTwo;
  @FXML public Rectangle suspectThree;
  @FXML public Rectangle crimescene;
  @FXML public Rectangle suspectOne;
  @FXML public Rectangle buttonAccuse;

  /**
   * Initializes the map view.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  public void initialize() throws ApiProxyException {
    // Any required initialization code can be placed here
  }

  public void handleRectangleClicked() {}

  public void handleGuessClicked() throws IOException {
    App.openGuessScreen(buttonAccuse);
  }
}

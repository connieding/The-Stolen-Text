package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;

public class MapController extends Controller {

  @FXML public AnchorPane mapPane;
  @FXML public ImageView mapBg;
  @FXML public Text mapHead;
  @FXML public Rectangle collector;
  @FXML public Rectangle archivist;
  @FXML public Rectangle crimescene;
  @FXML public Rectangle historian;

  /**
   * Initializes the map view.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  public void initialize() throws ApiProxyException {
    // Any required initialization code can be placed here
  }

  public void handleRectangleClicked(MouseEvent event) throws IOException {

    System.out.println("");
    Rectangle clickedRectangle = (Rectangle) event.getSource();

    App.openScene(clickedRectangle, clickedRectangle.getId());
  }
}

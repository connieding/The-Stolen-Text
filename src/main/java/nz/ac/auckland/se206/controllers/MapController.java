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

  @FXML private AnchorPane mapPane;
  @FXML private ImageView mapBg;
  @FXML private Text mapHead;
  @FXML private Rectangle collector;
  @FXML private Rectangle archivist;
  @FXML private Rectangle crimescene;
  @FXML private Rectangle historian;
  @FXML private ImageView collectorPortrait;
  @FXML private Text visitCollector;

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

  public void handleMouseEntered(MouseEvent event) {

    // Determine which scene on map is being hovered over
    Rectangle source = (Rectangle) event.getSource();
    String id = source.getId();

    // Show sorresponding suspect scene
    if (id.equals("collector")) {
      collectorPortrait.setOpacity(1);
      visitCollector.setOpacity(1);
    }
  }

  public void handleMouseExited(MouseEvent event) {

    // Determine which scene on map is being hovered over
    Rectangle source = (Rectangle) event.getSource();
    String id = source.getId();

    // Unshow corresponding suspect scene
    if (id.equals("collector")) {
      collectorPortrait.setOpacity(0);
      visitCollector.setOpacity(0);
    }
  }
}

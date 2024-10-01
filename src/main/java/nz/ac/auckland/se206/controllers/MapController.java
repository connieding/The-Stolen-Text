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
  @FXML private ImageView suspectShowBackground;
  @FXML private ImageView collectorPortrait;
  @FXML private Text visitCollector;
  @FXML private ImageView archivistPortrait;
  @FXML private Text visitArchivist;
  @FXML private ImageView historianPortrait;
  @FXML private Text visitHistorian;

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

    suspectShowBackground.setOpacity(1);

    // Show sorresponding suspect scene
    if (id.equals("collector")) {
      collectorPortrait.setOpacity(1);
      visitCollector.setOpacity(1);
    } else if (id.equals("archivist")) {
      archivistPortrait.setOpacity(1);
      visitArchivist.setOpacity(1);
    } else if (id.equals("historian")) {
      historianPortrait.setOpacity(1);
      visitHistorian.setOpacity(1);
    }
  
  }

  public void handleMouseExited(MouseEvent event) {

    // Determine which scene on map is being hovered over
    Rectangle source = (Rectangle) event.getSource();
    String id = source.getId();

    suspectShowBackground.setOpacity(0);

    // Unshow corresponding suspect scene
    if (id.equals("collector")) {
      collectorPortrait.setOpacity(0);
      visitCollector.setOpacity(0);
    } else if (id.equals("archivist")) {
      archivistPortrait.setOpacity(0);
      visitArchivist.setOpacity(0);
    } else if (id.equals("historian")) {
      historianPortrait.setOpacity(0);
      visitHistorian.setOpacity(0);
    }
  }
}

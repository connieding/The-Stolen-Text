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

/**
 * This class is the controller for the map scene. It handles the map and the corresponding scenes.
 */
public class MapController extends Controller {

  @FXML private AnchorPane mapPane;
  @FXML private ImageView mapBg;
  @FXML private Text mapHead;
  @FXML private Rectangle collector;
  @FXML private Rectangle archivist;
  @FXML private Rectangle crimescene;
  @FXML private Rectangle historian;
  @FXML private ImageView suspectShowBackground;
  @FXML private ImageView libraryImage;
  @FXML private Text visitLibrary;
  @FXML private ImageView collectorPortrait;
  @FXML private Text visitCollector;
  @FXML private ImageView archivistPortrait;
  @FXML private Text visitArchivist;
  @FXML private ImageView historianPortrait;
  @FXML private Text visitHistorian;
  @FXML private ImageView libraryIcon;
  @FXML private ImageView collectorIcon;
  @FXML private ImageView archivistIcon;
  @FXML private ImageView historianIcon;

  /**
   * Initializes the map view, setting the opacity of the suspect scenes to 0.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  public void initialize() throws ApiProxyException {
    // Any required initialization code can be placed here
  }

  /**
   * Handle rectangle click and open the corresponding scene.
   *
   * @param event the mouse event
   * @throws IOException if the scene is not found
   */
  public void handleRectangleClicked(MouseEvent event) throws IOException {
    SoundController.playSound();

    System.out.println("");
    Rectangle clickedRectangle = (Rectangle) event.getSource();

    App.openScene(clickedRectangle, clickedRectangle.getId());
  }

  /**
   * Show the corresponding suspect scene when the mouse enters the scene.
   *
   * @param event the mouse event
   */
  public void handleMouseEntered(MouseEvent event) {

    // Determine which scene on map is being hovered over
    Rectangle source = (Rectangle) event.getSource();
    String id = source.getId();

    suspectShowBackground.setOpacity(1);

    // Show sorresponding suspect scene
    if (id.equals("collector")) {
      collectorPortrait.setOpacity(1);
      visitCollector.setOpacity(1);
      collectorIcon.setOpacity(0.7);
    } else if (id.equals("archivist")) {
      archivistPortrait.setOpacity(1);
      visitArchivist.setOpacity(1);
      archivistIcon.setOpacity(0.7);
    } else if (id.equals("historian")) {
      historianPortrait.setOpacity(1);
      visitHistorian.setOpacity(1);
      historianIcon.setOpacity(0.7);
    } else {
      libraryImage.setOpacity(1);
      visitLibrary.setOpacity(1);
      libraryIcon.setOpacity(0.7);
    }
  }

  /**
   * Unshow the corresponding suspect scene when the mouse exits the scene.
   *
   * @param event the mouse event
   */
  public void handleMouseExited(MouseEvent event) {

    // Determine which scene on map is being hovered over
    Rectangle source = (Rectangle) event.getSource();
    String id = source.getId();

    suspectShowBackground.setOpacity(0);

    // Unshow corresponding suspect scene
    if (id.equals("collector")) {
      collectorPortrait.setOpacity(0);
      visitCollector.setOpacity(0);
      collectorIcon.setOpacity(1);
    } else if (id.equals("archivist")) {
      archivistPortrait.setOpacity(0);
      visitArchivist.setOpacity(0);
      archivistIcon.setOpacity(1);
    } else if (id.equals("historian")) {
      historianPortrait.setOpacity(0);
      visitHistorian.setOpacity(0);
      historianIcon.setOpacity(1);
    } else {
      libraryImage.setOpacity(0);
      visitLibrary.setOpacity(0);
      libraryIcon.setOpacity(1);
    }
  }
}

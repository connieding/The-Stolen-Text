package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameData;

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
    String lastClickedId = GameData.getLastClickedRectangleId();

    if (lastClickedId.equals("collector")) {
      disableRectangle(collector, collectorIcon, "collectorIconDisabled.png");
    } else if (lastClickedId.equals("archivist")) {
      disableRectangle(archivist, archivistIcon, "archivistIconDisabled.png");
    } else if (lastClickedId.equals("historian")) {
      disableRectangle(historian, historianIcon, "historianIconDisabled.png");
    } else {
      disableRectangle(crimescene, libraryIcon, "crimeIconDisabled.png"); // crimescene
    }
  }

  /**
   * Handle rectangle click and open the corresponding scene.
   *
   * @param event the mouse event
   * @throws IOException if the scene is not found
   */
  public void handleRectangleClicked(MouseEvent event) throws IOException {
    SoundController.playSound();

    Rectangle clickedRectangle = (Rectangle) event.getSource();

    ImageView clickedIcon = getIconForRectangle(clickedRectangle); // Get the correct icon

    // Disable the clicked rectangle and update its icon
    disableRectangle(clickedRectangle, clickedIcon, getDisabledIconFileName(clickedRectangle));

    // Enable the previously clicked rectangle and restore its icon
    String lastClickedId = GameData.getLastClickedRectangleId();
    if (!clickedRectangle.getId().equals(lastClickedId)) {
      enablePreviousRectangle(lastClickedId);
    }

    // Update GameData with the new last clicked rectangle
    GameData.setLastClickedRectangleId(clickedRectangle.getId());

    App.openScene(clickedRectangle, clickedRectangle.getId());
  }

  /**
   * Enable the previous rectangle based on the last clicked rectangle ID and restore its icon.
   *
   * @param lastClickedId the ID of the last clicked rectangle
   */
  private void enablePreviousRectangle(String lastClickedId) {
    if (lastClickedId.equals("collector")) {
      enableRectangle(collector, collectorIcon, "/images/collectorIcon.png");
    } else if (lastClickedId.equals("archivist")) {
      enableRectangle(archivist, archivistIcon, "/images/archivistIcon.png");
    } else if (lastClickedId.equals("historian")) {
      enableRectangle(historian, historianIcon, "/images/historianIcon.png");
    } else {
      enableRectangle(crimescene, libraryIcon, "/images/crimeIcon.png"); // crimescene
    }
  }

  /**
   * Disables a rectangle and changes its icon.
   *
   * @param rectangle the rectangle to disable
   * @param icon the associated icon to change
   * @param disabledIconFile the file name of the disabled icon
   */
  private void disableRectangle(Rectangle rectangle, ImageView icon, String disabledIconFile) {
    rectangle.setDisable(true);
    icon.setImage(new Image(getClass().getResourceAsStream("/images/" + disabledIconFile)));
  }

  /**
   * Enables a rectangle and restores its original icon.
   *
   * @param rectangle the rectangle to enable
   * @param icon the associated icon to restore
   * @param enabledIconFile the file name of the original icon
   */
  private void enableRectangle(Rectangle rectangle, ImageView icon, String enabledIconFile) {
    rectangle.setDisable(false);
    icon.setImage(new Image(getClass().getResourceAsStream(enabledIconFile)));
  }

  /**
   * Get the icon file name for the disabled state based on the rectangle.
   *
   * @param rectangle the rectangle that was clicked
   * @return the file name of the corresponding disabled icon
   */
  private String getDisabledIconFileName(Rectangle rectangle) {
    if (rectangle == collector) {
      return "collectorIconDisabled.png";
    } else if (rectangle == archivist) {
      return "archivistIconDisabled.png";
    } else if (rectangle == historian) {
      return "historianIconDisabled.png";
    } else {
      return "crimeIconDisabled.png"; // crimescene
    }
  }

  /**
   * Get the icon associated with a rectangle.
   *
   * @param rectangle the rectangle
   * @return the ImageView associated with the rectangle
   */
  private ImageView getIconForRectangle(Rectangle rectangle) {
    if (rectangle == collector) {
      return collectorIcon;
    } else if (rectangle == archivist) {
      return archivistIcon;
    } else if (rectangle == historian) {
      return historianIcon;
    } else {
      return libraryIcon; // crimescene
    }
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

    // Show corresponding suspect scene
    if (id.equals("collector")) {
      collectorPortrait.setOpacity(1);
      visitCollector.setOpacity(1);
      collectorIcon.setOpacity(0.8);
    } else if (id.equals("archivist")) {
      archivistPortrait.setOpacity(1);
      visitArchivist.setOpacity(1);
      archivistIcon.setOpacity(0.8);
    } else if (id.equals("historian")) {
      historianPortrait.setOpacity(1);
      visitHistorian.setOpacity(1);
      historianIcon.setOpacity(0.8);
    } else {
      libraryImage.setOpacity(1);
      visitLibrary.setOpacity(1);
      libraryIcon.setOpacity(0.8);
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

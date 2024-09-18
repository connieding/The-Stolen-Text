package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class GuessController {

  @FXML private ImageView archivistSelect;
  @FXML private ImageView collectorSelect;
  @FXML private ImageView historianSelect;

  @FXML private ImageView circleArchivist;
  @FXML private ImageView circleCollector;
  @FXML private ImageView circleHistorian;

  private String selectedSuspect = null;

  @FXML
  public void initialize() {
    hideAllCircles();

    archivistSelect.setOnMouseClicked(event -> setSelectedSuspect("Archivist"));
    collectorSelect.setOnMouseClicked(event -> setSelectedSuspect("Collector"));
    historianSelect.setOnMouseClicked(event -> setSelectedSuspect("Historian"));
  }

  private void setSelectedSuspect(String suspect) {
    this.selectedSuspect = suspect;

    // Hide all circles and reset opacity
    hideAllCircles();
    archivistSelect.setOpacity(0.5);
    collectorSelect.setOpacity(0.5);
    historianSelect.setOpacity(0.5);

    // Show the circle for the selected suspect and set opacity
    if (suspect.equals("Archivist")) {
      archivistSelect.setOpacity(1.0);
      circleArchivist.setVisible(true);
    } else if (suspect.equals("Collector")) {
      collectorSelect.setOpacity(1.0);
      circleCollector.setVisible(true);
    } else if (suspect.equals("Historian")) {
      historianSelect.setOpacity(1.0);
      circleHistorian.setVisible(true);
    }
  }

  private void hideAllCircles() {
    circleArchivist.setVisible(false);
    circleCollector.setVisible(false);
    circleHistorian.setVisible(false);
  }
}

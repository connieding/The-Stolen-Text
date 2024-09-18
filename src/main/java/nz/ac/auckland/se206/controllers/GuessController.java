package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class GuessController {

  @FXML private ImageView archivistSelect;
  @FXML private ImageView collectorSelect;
  @FXML private ImageView historianSelect;

  private String selectedSuspect = null;

  @FXML
  public void initialize() {
    // Add click event handlers for the suspect images
    archivistSelect.setOnMouseClicked(event -> setSelectedSuspect("Archivist"));
    collectorSelect.setOnMouseClicked(event -> setSelectedSuspect("Collector"));
    historianSelect.setOnMouseClicked(event -> setSelectedSuspect("Historian"));
  }

  // Method to set the selected suspect and update the UI
  private void setSelectedSuspect(String suspect) {
    this.selectedSuspect = suspect;

    // Reset the opacity of all images
    archivistSelect.setOpacity(0.5);
    collectorSelect.setOpacity(0.5);
    historianSelect.setOpacity(0.5);

    // Set the selected suspect image to full opacity
    if (suspect.equals("Archivist")) {
      archivistSelect.setOpacity(1.0);
    } else if (suspect.equals("Collector")) {
      collectorSelect.setOpacity(1.0);
    } else if (suspect.equals("Historian")) {
      historianSelect.setOpacity(1.0);
    }
  }
}

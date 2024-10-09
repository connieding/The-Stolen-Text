package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.scene.Node;
import nz.ac.auckland.se206.App;

public class IntroController {

  public void initialize() {
    // Any required initialization code can be placed here
  }

  public void handleRectangleClicked(Node event) throws IOException {
    App.hideOverlay(event);
  }
}

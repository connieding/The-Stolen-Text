package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;

public abstract class ClueController extends Controller {

  @FXML protected ImageView buttonReturn;

  @Override
  public void initialize() throws ApiProxyException {}

  public void handleRectangleClicked(MouseEvent event) throws IOException {
    App.openScene(buttonReturn, "crimescene");
  }
}

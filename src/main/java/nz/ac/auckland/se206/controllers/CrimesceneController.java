package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;

public class CrimesceneController extends Controller {

  @FXML private AnchorPane crimescenePane;
  @FXML private ImageView crimesceneBg;
  @FXML private Rectangle buttonMap;
  @FXML private Rectangle clueOne;
  @FXML private Rectangle clueTwo;
  @FXML private Rectangle clueThree;
  @FXML private Text crimesceneHead;
  @FXML private Rectangle buttonAccuse;
  @FXML private Label lblTimer;
  @FXML private AnchorPane mapSubScene;

  @Override
  public void initialize() throws ApiProxyException {}

  public void handleRectangleClicked(MouseEvent event) throws IOException {
    App.openScene(((Rectangle) event.getSource()), ((Rectangle) event.getSource()).getId());
  }
}

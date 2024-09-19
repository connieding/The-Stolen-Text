package nz.ac.auckland.se206.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ClueGlassController extends ClueController implements Initializable {

  @FXML private Rectangle buttonReturn;
  @FXML private ImageView imgGlass1;
  @FXML private ImageView imgGlass2;
  @FXML private ImageView imgGlass3;
  @FXML private ImageView imgGlass4;

  Draggable draggable = new Draggable();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    draggable.makeDraggable(imgGlass1);
    draggable.makeDraggable(imgGlass2);
    draggable.makeDraggable(imgGlass3);
    draggable.makeDraggable(imgGlass4);
  }
}

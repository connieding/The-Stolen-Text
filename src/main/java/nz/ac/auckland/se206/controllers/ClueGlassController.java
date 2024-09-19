package nz.ac.auckland.se206.controllers;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;

public class ClueGlassController extends ClueController implements Initializable {

  @FXML private Rectangle buttonReturn;
  @FXML private ImageView imgGlass1;
  @FXML private ImageView imgGlass2;
  @FXML private ImageView imgGlass3;
  @FXML private ImageView imgGlass4;

  private static HashMap<Node, Boolean> draggedMap = new HashMap<>();
  private static MediaPlayer cluePlayer;
  private static boolean hasPlayed = false;

  Draggable draggable = new Draggable();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    draggable.makeDraggable(imgGlass1);
    draggable.makeDraggable(imgGlass2);
    draggable.makeDraggable(imgGlass3);
    draggable.makeDraggable(imgGlass4);
    draggedMap.put(imgGlass1, false);
    draggedMap.put(imgGlass2, false);
    draggedMap.put(imgGlass3, false);
    draggedMap.put(imgGlass4, false);
  }

  public static void setDraggedMap(Node imgGlass, boolean isDragged) {
    draggedMap.replace(imgGlass, isDragged);
    if (!draggedMap.containsValue(false) && !hasPlayed) {
      hasPlayed = true;
      // All glasses are dragged
      // Play sound
      try {
        Media clueVoice =
            new Media(App.class.getResource("/sounds/hairClue.mp3").toURI().toString());
        cluePlayer = new MediaPlayer(clueVoice);
        cluePlayer.play();
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    }
  }
}

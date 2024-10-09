package nz.ac.auckland.se206.controllers;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameData;

/** This class is the controller for the glass clue scene. It handles the glass shards. */
public class ClueGlassController extends ClueController implements Initializable {

  private static HashMap<Node, Boolean> draggedMap = new HashMap<>();
  private static boolean hasPlayed = false;

  /** Set the glass shard as dragged. */
  public static void setDraggedMap(Node imgGlass, boolean isDragged) {

    // Set the current shard to dragged
    draggedMap.replace(imgGlass, isDragged);

    // Check if all shards are newly dragged
    if (!draggedMap.containsValue(false) && !hasPlayed) {
      hasPlayed = true;

      // Set the clue as used
      GameData.setUsedClue(true);

      // All glasses are dragged
      // Play sound
      try {
        ClueController.playClue(App.class.getResource("/sounds/hairClue.mp3").toURI().toString());
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    }
  }

  @FXML private ImageView buttonReturn;
  @FXML private ImageView imgGlass1;
  @FXML private ImageView imgGlass2;
  @FXML private ImageView imgGlass3;
  @FXML private ImageView imgGlass4;
  private Draggable draggable = new Draggable();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    // Make the glass shard draggable
    draggable.makeDraggable(imgGlass1);
    draggable.makeDraggable(imgGlass2);
    draggable.makeDraggable(imgGlass3);
    draggable.makeDraggable(imgGlass4);

    // Set the glass shards as not dragged
    draggedMap.put(imgGlass1, false);
    draggedMap.put(imgGlass2, false);
    draggedMap.put(imgGlass3, false);
    draggedMap.put(imgGlass4, false);
  }

  public static void playGlassDropSound() {
    try {
      ClueController.playEffect(App.class.getResource("/sounds/glassDrop.mp3").toURI().toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }
}

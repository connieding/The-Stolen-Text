package nz.ac.auckland.se206.controllers;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameData;

/** This class is the controller for the glass clue scene. It handles the glass shards. */
public class ClueGlassController extends ClueController implements Initializable {

  private static HashMap<Node, Boolean> draggedMap = new HashMap<>();
  private static boolean isDragged = false;
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

  /** Play the glass dropping sound. */
  public static void playGlassDropSound() {
    try {
      ClueController.playEffect(App.class.getResource("/sounds/glassDrop.mp3").toURI().toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  /** Set the glass shards as dragged. */
  public static void isDragged() {
    isDragged = true;
  }

  @FXML private ImageView buttonReturn;
  @FXML private ImageView imgGlass1;
  @FXML private ImageView imgGlass2;
  @FXML private ImageView imgGlass3;
  @FXML private ImageView imgGlass4;
  @FXML private ImageView arrow;
  private Draggable draggable = new Draggable();
  private int clickCount = 0;
  private boolean played = false;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    setupClickEvent(imgGlass1);
    setupClickEvent(imgGlass2);
    setupClickEvent(imgGlass3);
    setupClickEvent(imgGlass4);

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

    played = false;
    isDragged = false;
  }

  // New method to handle clicks on glass shards
  private void setupClickEvent(ImageView imgGlass) {

    imgGlass.setOnMouseClicked(
        event -> {
          if (isDragged) {
            return;
          }
          clickCount++;
          if (clickCount == 3) {
            if (!played) {
              moveArrow();
              played = true;
            }
          }
        });
  }

  /** Move the arrow hint for the glass clue. */
  public void moveArrow() {
    arrow.setVisible(true);

    TranslateTransition translate = new TranslateTransition();
    translate.setNode(arrow);

    // Set the arrow to move to the right
    translate.setByX(90);
    translate.setByY(-90);

    // Set the duration and interpolator of the arrow
    translate.setDuration(javafx.util.Duration.seconds(1.4));
    translate.setInterpolator(Interpolator.EASE_BOTH);

    // Hide the arrow when the animation is finished
    translate.setOnFinished(
        event -> {
          arrow.setVisible(false);
          arrow.setTranslateX(0);
          arrow.setTranslateY(0);
        });

    // Play the animation
    translate.play();
  }
}

package nz.ac.auckland.se206.controllers;

import java.net.URISyntaxException;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameData;

/** This class is the controller for the paper clue scene. It handles the handkerchief. */
public class CluePaperController extends ClueController {

  @FXML private ImageView imageAnimate;
  @FXML private ImageView imageHandkerchief;
  @FXML private ImageView arrow;

  private int clickCount = 0;
  private Timeline arrowTimeline;

  /** Initialize the paper scene to animate the handkerchief. */
  @FXML
  void handleClickHandkerchief(MouseEvent event) {

    // Increment the click count
    clickCount++;

    // Hide the original background handkerchief image after the first click
    if (clickCount == 1) {
      imageHandkerchief.setVisible(false);
    }

    // Load the images to animate
    Image[] images = {
      new Image(getClass().getResourceAsStream("/images/frame1.png")),
      new Image(getClass().getResourceAsStream("/images/frame2.png")),
      new Image(getClass().getResourceAsStream("/images/frame3.png")),
      new Image(getClass().getResourceAsStream("/images/frame4.png")),
      new Image(getClass().getResourceAsStream("/images/frame5.png")),
      new Image(getClass().getResourceAsStream("/images/handkerchief.png")),
    };

    // Animate the images
    if (clickCount <= images.length) {
      imageAnimate.setImage(images[clickCount - 1]);
      if (clickCount <= images.length - 1) {
        showArrowAfterDelay();
      }
      try {
        ClueController.playEffect(
            App.class.getResource("/sounds/clothFold.mp3").toURI().toString());
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    }

    // Play the clue voice after the last image is shown
    if (clickCount == images.length) {

      // Set the clue as used
      GameData.setUsedClue(true);

      try {
        super.playClue(App.class.getResource("/sounds/handkerchiefClue.mp3").toURI().toString());
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    }
  }

  private void showArrowAfterDelay() {
    // Stop the existing timeline if it's running
    if (arrowTimeline != null) {
      arrowTimeline.stop();
    } else if (clickCount >= 6) {
      arrowTimeline.stop();
      return;
    }

    // Only show the arrow if not on the last image
    if (clickCount < 5) {
      // Create a new timeline for showing the arrow
      arrowTimeline =
          new Timeline(
              new KeyFrame(
                  Duration.seconds(6),
                  event -> {
                    arrow.setVisible(true); // Make the arrow visible
                    playClickAnimation(arrow);
                  }));
      arrowTimeline.setCycleCount(1);
      arrowTimeline.play(); // Start the timeline
    }
  }

  private void playClickAnimation(ImageView arrow) {
    ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), arrow);
    scaleTransition.setFromX(1.0);
    scaleTransition.setFromY(1.0);
    scaleTransition.setToX(1.1);
    scaleTransition.setToY(1.1);
    scaleTransition.setAutoReverse(true); // Reverse the animation

    // Set the cycle count to 4 to make it grow and shrink twice
    scaleTransition.setCycleCount(4);

    // Add a listener to reset the visibility after the animation finishes
    scaleTransition.setOnFinished(
        event -> {
          arrow.setVisible(false);
        });
    scaleTransition.play();
  }
}

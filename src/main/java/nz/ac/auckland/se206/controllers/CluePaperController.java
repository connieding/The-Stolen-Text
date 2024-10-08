package nz.ac.auckland.se206.controllers;

import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameData;

/** This class is the controller for the paper clue scene. It handles the handkerchief. */
public class CluePaperController extends ClueController {

  @FXML private ImageView imageAnimate;
  @FXML private ImageView imageHandkerchief;

  private int clickCount = 0;

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

    // Animate the images
    if (clickCount <= images.length) {
      imageAnimate.setImage(images[clickCount - 1]);
    }
  }
}

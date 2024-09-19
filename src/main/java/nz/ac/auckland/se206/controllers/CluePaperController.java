package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CluePaperController extends ClueController {

  @FXML private ImageView imageAnimate;
  @FXML private ImageView imageHandkerchief;

  private int clickCount = 0;

  @FXML
  void handleClickHandkerchief(MouseEvent event) {
    clickCount++;

    if (clickCount == 1) {
      imageHandkerchief.setVisible(false);
    }

    Image[] images = {
      new Image(getClass().getResourceAsStream("/images/frame1.png")),
      new Image(getClass().getResourceAsStream("/images/frame2.png")),
      new Image(getClass().getResourceAsStream("/images/frame3.png")),
      new Image(getClass().getResourceAsStream("/images/frame4.png")),
      new Image(getClass().getResourceAsStream("/images/frame5.png")),
      new Image(getClass().getResourceAsStream("/images/handkerchief.png")),
    };

    if (clickCount <= images.length) {
      imageAnimate.setImage(images[clickCount - 1]);
    } else {
      clickCount = 0;
      imageAnimate.setImage(images[clickCount]);
    }
  }
}

package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class ClueBookshelfController extends ClueController {

  @FXML private Rectangle buttonClueBook;
  @FXML private ImageView imageBook;
  private boolean clicked = false;
  private boolean image = true;

  @FXML
  void bookClick(MouseEvent event) {
    if (image && !clicked) {
      imageBook.setImage(new Image(getClass().getResourceAsStream("/images/bookClose.png")));
    }
    image = !image;
    clicked = true;
  }

  @FXML
  void openBook(MouseEvent event) {
    if (!image) {
      imageBook.setImage(new Image(getClass().getResourceAsStream("/images/bookOpen.png")));
    } else if (clicked && image) {
      imageBook.setImage(new Image(getClass().getResourceAsStream("/images/bookClose.png")));
    }
    image = !image;
  }
}

package nz.ac.auckland.se206.controllers;

import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameData;

public class ClueBookshelfController extends ClueController {

  @FXML private Rectangle buttonClueBook;
  @FXML private ImageView imageBook;
  @FXML private ImageView crimesceneBg;

  // Book has been found
  private boolean clicked = false;

  // Book is open/closed
  private boolean image = true;

  // Sound has been played
  private boolean sound = false;
  private double mouseX;

  /** Initialize the book scene to open on drag */
  public void initialize() {
    // Set the mouse X coordinate when the mouse is pressed
    imageBook.setOnMousePressed(
        mouseEvent -> {
          mouseX = mouseEvent.getX();
        });
    // If mouse coordinate change horizontally, open/close the book
    imageBook.setOnMouseReleased(
        mouseEvent -> {
          if (mouseEvent.getX() <= mouseX - 80 & !image) {
            openBook(mouseEvent);
          } else if (mouseEvent.getX() >= mouseX + 80 & image) {
            openBook(mouseEvent);
          }
        });
  }

  /**
   * Highlight the book
   *
   * @param event
   */
  @FXML
  public void highlightBook(MouseEvent event) {
    if (!clicked) {
      buttonClueBook.setOpacity(0.7);
    }
  }

  /**
   * Unhighlight the book
   *
   * @param event
   */
  @FXML
  public void unhighlightBook(MouseEvent event) {
    if (!clicked) {
      buttonClueBook.setOpacity(0);
    }
  }

  @FXML
  public void bookClick(MouseEvent event) {

    // If the book has not been found, and the book is clicked
    if (image && !clicked) {

      // Set the background to have the book removed
      crimesceneBg.setImage(
          new Image(getClass().getResourceAsStream("/images/bookshelfRemoved.jpg")));

      // Show the closed book
      imageBook.setImage(new Image(getClass().getResourceAsStream("/images/bookClose.png")));
      buttonClueBook.setOpacity(0);
    }

    // Set the book to have been found, and closed
    image = !image;
    clicked = true;
  }

  @FXML
  public void openBook(MouseEvent event) {

    // If the book is closed, and the book is clicked
    if (!image) {

      // Show the open book
      imageBook.setImage(new Image(getClass().getResourceAsStream("/images/bookOpen.png")));

      // Set the clue as used
      GameData.setUsedClue(true);

      // If the sound has not been played yet
      if (!sound) {
        try {
          ClueController.playClue(App.class.getResource("/sounds/bookClue.mp3").toURI().toString());
        } catch (URISyntaxException e) {
          e.printStackTrace();
        }
      }

      // If the book is open, and the book is clicked
    } else if (clicked && image) {

      // Show the closed book
      imageBook.setImage(new Image(getClass().getResourceAsStream("/images/bookClose.png")));
    }

    // Set the book to be open/closed
    image = !image;
  }
}

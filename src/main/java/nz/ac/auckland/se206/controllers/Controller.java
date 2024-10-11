package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameData;

/**
 * This class is the controller for the scenes. It handles the timer, guess button, and map button.
 */
public abstract class Controller {

  @FXML protected Label lblTimer;
  @FXML protected Rectangle buttonAccuse;
  @FXML protected AnchorPane mapSubScene;
  @FXML protected Rectangle buttonMap;
  @FXML protected ImageView mapImage;
  @FXML protected ImageView accuseImage;

  private MediaPlayer hintPlayer;

  public abstract void initialize() throws ApiProxyException;

  /**
   * Set the time remaining on the timer.
   *
   * @param timeRemaining the time remaining
   */
  public void setTime(String timeRemaining) {
    lblTimer.setText(timeRemaining);
  }

  /**
   * Handle guess click and ensure that the player has met all suspects and used a clue before.
   *
   * @param event the mouse event
   * @throws IOException if the guess scene is not found
   */
  public void handleGuessClicked(MouseEvent event) throws IOException {
    SoundController.playSound();

    // Check if the player has met all suspects and used a clue before allowing them to guess
    if (GameData.hasUsedClue()
        & GameData.hasMetSuspect("archivist")
        & GameData.hasMetSuspect("collector")
        & GameData.hasMetSuspect("historian")) {
      App.openGuessScene(buttonAccuse);
      // Player has not interacted with a clue, play a sound to prevent guessing
    } else if (!GameData.hasUsedClue()) {
      try {
        ClueController.silence();
        Media hintVoice =
            new Media(App.class.getResource("/sounds/guessPreventerTwo.mp3").toURI().toString());
        hintPlayer = new MediaPlayer(hintVoice);
        hintPlayer.play();
      } catch (Exception e) {
        e.printStackTrace();
      }
      // Player has not interacted with all suspects, play a sound to prevent guessing
    } else {
      try {
        ClueController.silence();
        Media hintVoice =
            new Media(App.class.getResource("/sounds/guessPreventerOne.mp3").toURI().toString());
        hintPlayer = new MediaPlayer(hintVoice);
        hintPlayer.play();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Handle map click and toggle the map visibility.
   *
   * @param event the mouse event
   * @throws IOException if the map scene is not found
   */
  public void handleMapClicked(MouseEvent event) throws IOException {
    SoundController.playSound();

    // Toggle the map overlay
    if (!mapSubScene.isVisible()) {
      App.overlayMap();
      mapSubScene.setVisible(true);
      mapSubScene.setDisable(false);
    } else {
      App.hideOverlay();
      mapSubScene.setVisible(false);
      mapSubScene.setDisable(true);
    }
  }

  /**
   * Handle the accuse button click and ensure that the player has met all suspects and used a clue
   * before.
   *
   * @throws IOException
   */
  public void showInfo() throws IOException {
    // show map overlay
    mapSubScene.setVisible(true);
    mapSubScene.setDisable(false);
  }

  /** Handle the map button hover rotate and enlarge. */
  @FXML
  private void handleMouseEnterMap() {
    // Rotate and enlarge the map button
    mapImage.setRotate(17);
    mapImage.setScaleX(1.2);
    mapImage.setScaleY(1.2);
  }

  /** Handle the map button hover exited to set back. */
  @FXML
  private void handleMouseExitMap() {
    // Set the map button back to normal
    mapImage.setRotate(0);
    mapImage.setScaleX(1);
    mapImage.setScaleY(1);
  }

  /** Handle the accuse button hover to rotate and enlarge. */
  @FXML
  private void handleMouseEnterAccuse() {
    // Rotate and enlarge the accuse button if the player has met all suspects and used a clue
    if (GameData.hasUsedClue()
        & GameData.hasMetSuspect("archivist")
        & GameData.hasMetSuspect("collector")
        & GameData.hasMetSuspect("historian")) {
      accuseImage.setRotate(17);
      accuseImage.setScaleX(1.2);
      accuseImage.setScaleY(1.2);
    }
  }

  /** Handle the accuse button hover exited to set back. */
  @FXML
  private void handleMouseExitAccuse() {
    // Set the accuse button back to normal
    accuseImage.setRotate(0);
    accuseImage.setScaleX(1);
    accuseImage.setScaleY(1);
  }
}

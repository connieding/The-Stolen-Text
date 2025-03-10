package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;

/** This class is the controller for the clue scenes. It handles the clues and the return button. */
public abstract class ClueController extends Controller {

  private static MediaPlayer cluePlayer;
  private static MediaPlayer effectPlayer;

  /** Stop the clue audio file. */
  public static void silence() {
    if (cluePlayer != null) {
      cluePlayer.stop();
    }
  }

  /**
   * Play the clue audio file.
   *
   * @param path the path to the audio file
   */
  public static void playClue(String path) {
    silence();
    Media clueVoice = new Media(path);
    cluePlayer = new MediaPlayer(clueVoice);
    cluePlayer.play();
  }

  /**
   * Play the effect audio file.
   *
   * @param path the path to the audio file
   */
  public static void playEffect(String path) {
    Media effectVoice = new Media(path);
    effectPlayer = new MediaPlayer(effectVoice);
    effectPlayer.play();
  }

  @FXML protected ImageView buttonReturn;

  @Override
  public void initialize() throws ApiProxyException {}

  /* 
   * Handle the return button hover to set opacity low.
   */
  @FXML
  private void handleMouseEnterReturn() {
    buttonReturn.setOpacity(0.7);
  }

  /* 
   * Handle the return button hover exited to set opacity back up.
   */
  @FXML
  private void handleMouseExitReturn() {
    buttonReturn.setOpacity(1.0);
  }

  /**
   * Handle the return button click and open the crimescene scene.
   *
   * @param event the mouse event
   * @throws IOException if the crimescene scene is not found
   */
  public void handleRectangleClicked(MouseEvent event) throws IOException {
    SoundController.playSound();
    App.openScene(buttonReturn, "crimescene");
  }
}

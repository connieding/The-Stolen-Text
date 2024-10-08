package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;

public abstract class ClueController extends Controller {

  private static MediaPlayer cluePlayer;
  private static MediaPlayer effectPlayer;

  public static void silence() {
    if (cluePlayer != null) {
      cluePlayer.stop();
    }
  }

  public static void playClue(String path) {
    silence();
    Media clueVoice = new Media(path);
    cluePlayer = new MediaPlayer(clueVoice);
    cluePlayer.play();
  }

  public static void playEffect(String path) {
    Media effectVoice = new Media(path);
    effectPlayer = new MediaPlayer(effectVoice);
    effectPlayer.play();
  }

  @FXML protected ImageView buttonReturn;

  @Override
  public void initialize() throws ApiProxyException {}

  public void handleRectangleClicked(MouseEvent event) throws IOException {
    SoundController.playSound();
    App.openScene(buttonReturn, "crimescene");
  }
}

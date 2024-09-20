package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;

public abstract class ClueController extends Controller {

  private static MediaPlayer cluePlayer;

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

  @FXML protected Rectangle buttonReturn;

  @Override
  public void initialize() throws ApiProxyException {}

  public void handleRectangleClicked(MouseEvent event) throws IOException {
    App.openScene(buttonReturn, "crimescene");
  }
}

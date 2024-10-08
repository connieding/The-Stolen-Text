package nz.ac.auckland.se206.controllers;

import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;

public abstract class SoundController extends Controller {

  private static MediaPlayer sound;

  public static void silence() {
    if (sound != null) {
      sound.stop();
    }
  }

  public static void preloadSound() {
    try {
      // Load the sound resource once
      Media effect = new Media(App.class.getResource("/sounds/click.mp3").toURI().toString());
      sound = new MediaPlayer(effect);
    } catch (NullPointerException | URISyntaxException e) {
      e.printStackTrace();
    }
  }

  public static void playSound() {
    if (sound != null) {
      // Silence any currently playing sound
      silence();

      // Play the sound
      sound.seek(Duration.ZERO); // Reset to the beginning
      sound.play();
    } else {
      System.out.println("Sound not preloaded!");
    }
  }
}

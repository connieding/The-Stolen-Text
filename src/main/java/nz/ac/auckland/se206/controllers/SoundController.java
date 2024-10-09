package nz.ac.auckland.se206.controllers;

import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;

public abstract class SoundController extends Controller {

  private static MediaPlayer click;
  private static MediaPlayer circle;

  public static void silence() {
    if (click != null) {
      click.stop();
    }
  }

  public static void preloadSound() {
    try {
      // Load the sound resource once
      Media effect = new Media(App.class.getResource("/sounds/click.mp3").toURI().toString());
      click = new MediaPlayer(effect);
      Media effect2 = new Media(App.class.getResource("/sounds/circle.mp3").toURI().toString());
      circle = new MediaPlayer(effect2);
    } catch (NullPointerException | URISyntaxException e) {
      e.printStackTrace();
    }
  }

  public static void playSound() {
    if (click != null) {
      // Silence any currently playing sound
      silence();

      // Play the sound
      click.seek(Duration.ZERO); // Reset to the beginning
      click.play();
    } else {
      System.out.println("Sound not preloaded!");
    }
  }

  public static void playCircle() {
    if (circle != null) {
      // Silence any currently playing sound
      silence();

      // Play the sound
      circle.seek(Duration.ZERO); // Reset to the beginning
      circle.play();
    } else {
      System.out.println("Sound not preloaded!");
    }
  }
}

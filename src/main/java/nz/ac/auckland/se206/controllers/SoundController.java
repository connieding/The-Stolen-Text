package nz.ac.auckland.se206.controllers;

import java.net.URISyntaxException;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;

/**
 * This class is the controller for the sound effects. It handles the sound effects for the game.
 */
public abstract class SoundController extends Controller {

  private static MediaPlayer click;
  private static MediaPlayer click2;
  private static MediaPlayer click3;
  private static MediaPlayer circle;

  // Random number generator
  public static Random random = new Random();

  /** Stop the currently playing sound. */
  public static void silence() {
    if (click != null) {
      click.stop();
    }
    if (click2 != null) {
      click2.stop();
    }
    if (click3 != null) {
      click3.stop();
    }
  }

  /** Preload the sound resources to remove delay. */
  public static void preloadSound() {
    try {
      // Load the sound resource once
      Media effect = new Media(App.class.getResource("/sounds/click.mp3").toURI().toString());
      click = new MediaPlayer(effect);
      Media effect2 = new Media(App.class.getResource("/sounds/click2.mp3").toURI().toString());
      click2 = new MediaPlayer(effect2);
      Media effect3 = new Media(App.class.getResource("/sounds/click3.mp3").toURI().toString());
      click3 = new MediaPlayer(effect3);
      Media effect4 = new Media(App.class.getResource("/sounds/circle.mp3").toURI().toString());
      circle = new MediaPlayer(effect4);
    } catch (NullPointerException | URISyntaxException e) {
      e.printStackTrace();
    }
  }

  /** Play a random click sound from the preloaded sounds. */
  public static void playSound() {

    // Randomly choose a click sound to play
    int soundChoice = random.nextInt(3);

    if (click != null || click2 != null || click3 != null) {
      // Silence any currently playing sound
      silence();

      // Play the sound
      click.seek(Duration.ZERO); // Reset to the beginning

      // Play the chosen click sound
      if (soundChoice == 0) {
        click.play();
      } else if (soundChoice == 1) {
        click2.play();
      } else {
        click3.play();
      }

    } else {
      System.out.println("Sound not preloaded!");
    }
  }

  /** Play the circle sound from the preloaded sounds. */
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

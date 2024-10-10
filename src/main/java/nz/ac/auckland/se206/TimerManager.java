package nz.ac.auckland.se206;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/** This class is used to manage the timer. */
public class TimerManager {

  private static TimerManager instance;

  /**
   * Get the instance of the TimerManager class.
   *
   * @param data the GameData object
   * @return the TimerManager instance
   */
  public static synchronized TimerManager getInstance(GameData data) {
    if (instance == null) {
      instance = new TimerManager(data);
    }
    return instance;
  }

  private GameData data;
  private ScheduledExecutorService scheduler;
  private ScheduledFuture<?> timerHandle;
  private int remainingTime;
  @FXML private Label timerLabel;

  /**
   * Constructor for the TimerManager class.
   *
   * @param data the GameData object
   */
  private TimerManager(GameData data) {
    this.data = data;
  }

  /**
   * Start the timer with the given number of seconds and label.
   *
   * @param seconds the number of seconds to set the timer to
   * @param timerLabel the label to display the timer
   */
  public void startTimer(int seconds, Label timerLabel) {

    // Stop the timer if it is already running
    if (timerHandle != null && !timerHandle.isDone()) {
      timerHandle.cancel(true);
    }

    // Initialize the timer variables
    this.timerLabel = timerLabel;
    this.remainingTime = seconds;

    // Start the timer
    scheduler = Executors.newScheduledThreadPool(1);
    timerHandle =
        scheduler.scheduleAtFixedRate(
            () -> {

              // While there is still time remaining, update the timer label
              if (remainingTime > 0) {
                remainingTime--;
                Platform.runLater(
                    () -> {
                      int min = remainingTime / 60;
                      int sec = remainingTime % 60;
                      if (timerLabel != null) {
                        timerLabel.setText(String.format("%d:%02d", min, sec));
                      }
                    });

                // Check if there's 1 minute left, then warning appears
                if (remainingTime < 60 && remainingTime > 53 && !GameData.isGuessing()) {
                  Platform.runLater(
                      () -> {
                        try {
                          showWarning();
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                      });
                } else if (remainingTime < 53 && !GameData.isGuessing()) {
                  Platform.runLater(
                      () -> {
                        try {
                          stopWarning();
                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                      });
                }

                // If the time is up, call the timeUp method in the GameData class
              } else {
                Platform.runLater(
                    () -> {
                      try {
                        data.timeUp(timerLabel);
                      } catch (Exception e) {
                        e.printStackTrace();
                      }
                    });

                // Stop the timer
                stopTimer();
              }
            },
            0,
            1,
            TimeUnit.SECONDS);
  }

  /**
   * Show the warning message, when there is 1 minute left.
   *
   * @throws IOException if the warning scene is not found
   */
  private void showWarning() throws IOException {
    App.overlayWarning();
  }

  /**
   * Stop the warning message, when there is 53 seconds left.
   *
   * @throws IOException if the warning scene is not found
   */
  private void stopWarning() throws IOException {
    App.hideWarning();
  }

  /**
   * Get the remaining time on the timer.
   *
   * @return the remaining time
   */
  public int getTime() {
    return remainingTime;
  }

  /** Stop the timer and set the instance to null. */
  public void stopTimer() {
    instance = null;
    if (timerHandle != null && !timerHandle.isDone()) {
      timerHandle.cancel(true);
    }
    if (scheduler != null) {
      scheduler.shutdown();
    }
  }
}

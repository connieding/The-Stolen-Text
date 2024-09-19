package nz.ac.auckland.se206;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TimerManager {

  private static TimerManager instance;

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

  private TimerManager(GameData data) {
    this.data = data;
  }

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

  public int getTime() {
    return remainingTime;
  }

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

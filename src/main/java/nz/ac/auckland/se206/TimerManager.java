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

  public static synchronized TimerManager getInstance() {
    if (instance == null) {
      instance = new TimerManager();
    }
    return instance;
  }

  private ScheduledExecutorService scheduler;
  private ScheduledFuture<?> timerHandle;
  private int remainingTime;
  @FXML private Label timerLabel;

  private TimerManager() {}

  public void startTimer(int seconds, GameStateContext context) {
    if (timerHandle != null && !timerHandle.isDone()) {
      timerHandle.cancel(true);
    }

    this.remainingTime = seconds;

    scheduler = Executors.newScheduledThreadPool(1);
    timerHandle =
        scheduler.scheduleAtFixedRate(
            () -> {
              if (remainingTime > 0) {
                remainingTime--;
                Platform.runLater(
                    () -> {
                      context.setTime(String.valueOf(remainingTime));
                    });
              } else {
                Platform.runLater(
                    () -> {
                      context.setTime("Time's up!");
                    });
                stopTimer();
              }
            },
            0,
            1,
            TimeUnit.SECONDS);
  }

  public void stopTimer() {
    if (timerHandle != null && !timerHandle.isDone()) {
      timerHandle.cancel(true);
    }
    if (scheduler != null) {
      scheduler.shutdown();
    }
  }
}

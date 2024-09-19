package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.scene.control.Label;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.controllers.GuessController;

public class GameData {

  private GuessController guessController;
  private static boolean metArchivist;
  private static boolean metCollector;
  private static boolean metHistorian;
  private static boolean usedClue;
  private static boolean isGuessing;

  public void timeUp(Label timerLabel) throws ApiProxyException, IOException {
    if (isGuessing) {
      guessController.handleSubmitClicked();
    } else {
      App.openGuessScene(timerLabel);
    }
  }

  public void setGuessController(GuessController guessController) {
    this.guessController = guessController;
  }

  public static boolean isGuessing() {
    return isGuessing;
  }

  public static boolean hasMetArchivist() {
    return metArchivist;
  }

  public static boolean hasMetCollector() {
    return metCollector;
  }

  public static boolean hasMetHistorian() {
    return metHistorian;
  }

  public static boolean hasUsedClue() {
    return usedClue;
  }

  public static void setMetArchivist(boolean metArchivist) {
    GameData.metArchivist = metArchivist;
  }

  public static void setMetCollector(boolean metCollector) {
    GameData.metCollector = metCollector;
  }

  public static void setMetHistorian(boolean metHistorian) {
    GameData.metHistorian = metHistorian;
  }

  public static void setUsedClue(boolean usedClue) {
    GameData.usedClue = usedClue;
  }

  public static void setGuessing(boolean guessing) {
    isGuessing = guessing;
  }
}

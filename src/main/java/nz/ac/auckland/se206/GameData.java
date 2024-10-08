package nz.ac.auckland.se206;

import java.io.IOException;
import java.util.HashMap;
import javafx.scene.control.Label;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.controllers.GuessController;

/** This class is used to store the game data and handle the game logic. */
public class GameData {

  private static boolean usedClue;
  private static boolean isGuessing;
  private static HashMap<String, Boolean> meetings = new HashMap<String, Boolean>();

  public static boolean isGuessing() {
    return isGuessing;
  }

  public static boolean hasMetSuspect(String suspect) {
    return meetings.get(suspect);
  }

  public static boolean hasUsedClue() {
    return usedClue;
  }

  public static void setMetSuspect(String suspect) {
    meetings.replace(suspect, true);
  }

  public static void setUsedClue(boolean usedClue) {
    GameData.usedClue = usedClue;
  }

  public static void setGuessing(boolean guessing) {
    isGuessing = guessing;
  }

  private GuessController guessController;

  /** Constructor for the GameData class. */
  public GameData() {
    meetings.put("archivist", false);
    meetings.put("collector", false);
    meetings.put("historian", false);
    usedClue = false;
    isGuessing = false;
  }

  /**
   * Handle the time up event. If the player is guessing, submit the guess. If the player has met
   * all suspects and used the clue, open the guess scene. If the player has not met all suspects,
   * open the failed scene.
   *
   * @param timerLabel the label to display the timer
   * @throws ApiProxyException if the API proxy is not working
   * @throws IOException if the scene is not found
   */
  public void timeUp(Label timerLabel) throws ApiProxyException, IOException {

    // If the player is guessing, submit the guess
    if (isGuessing) {
      guessController.handleSubmitClicked();

      // If the player has met all suspects and used the clue, open the guess scene
    } else if (GameData.hasUsedClue()
        & GameData.hasMetSuspect("archivist")
        & GameData.hasMetSuspect("collector")
        & GameData.hasMetSuspect("historian")) {

      App.openGuessScene(timerLabel);

      // If the player has not met all suspects, open the failed scene
    } else {
      App.openScene(timerLabel, "failed");
    }
  }

  /**
   * Set the guess controller.
   *
   * @param guessController the guess controller
   */
  public void setGuessController(GuessController guessController) {
    this.guessController = guessController;
  }
}

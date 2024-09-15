package nz.ac.auckland.se206;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.controllers.Controller;
import nz.ac.auckland.se206.controllers.CrimesceneController;
import nz.ac.auckland.se206.controllers.RoomController;
import nz.ac.auckland.se206.states.GameOver;
import nz.ac.auckland.se206.states.GameStarted;
import nz.ac.auckland.se206.states.GameState;
import nz.ac.auckland.se206.states.Guessing;

/**
 * Context class for managing the state of the game. Handles transitions between different game
 * states and maintains game data such as the professions and rectangle IDs.
 */
public class GameStateContext {

  private final String rectIdToGuess;
  private final String professionToGuess;
  private final Map<String, String> rectanglesToProfession;
  private final GameStarted gameStartedState;
  private final Guessing guessingState;
  private final GameOver gameOverState;
  private GameState gameState;
  private Controller currentScene;
  private static TimerManager timerManager;
  private Controller crimeScene;

  /** Constructs a new GameStateContext and initializes the game states and professions. */
  public GameStateContext(CrimesceneController crimeScene) {

    this.crimeScene = crimeScene;

    gameStartedState = new GameStarted(this);
    guessingState = new Guessing(this);
    gameOverState = new GameOver(this);

    gameState = gameStartedState; // Initial state

    String[] professions = {"historian", "archivist", "collector"};

    rectanglesToProfession = new HashMap<>();
    rectanglesToProfession.put("rectPerson1", professions[0]);
    rectanglesToProfession.put("rectPerson2", professions[1]);
    rectanglesToProfession.put("rectPerson3", professions[2]);

    rectIdToGuess = "rectPerson3";
    professionToGuess = rectanglesToProfession.get(rectIdToGuess);

    timerManager = TimerManager.getInstance();
    timerManager.startTimer(10, this);
  }

  public GameStateContext(RoomController crimeScene) {

    this.crimeScene = crimeScene;

    gameStartedState = new GameStarted(this);
    guessingState = new Guessing(this);
    gameOverState = new GameOver(this);

    gameState = gameStartedState; // Initial state

    String[] professions = {"historian", "archivist", "collector"};

    rectanglesToProfession = new HashMap<>();
    rectanglesToProfession.put("rectPerson1", professions[0]);
    rectanglesToProfession.put("rectPerson2", professions[1]);
    rectanglesToProfession.put("rectPerson3", professions[2]);

    rectIdToGuess = "rectPerson3";
    professionToGuess = rectanglesToProfession.get(rectIdToGuess);

    timerManager = TimerManager.getInstance();
    timerManager.startTimer(10, this);
  }

  /**
   * Sets the current state of the game.
   *
   * @param state the new state to set
   */
  public void setState(GameState state) {
    this.gameState = state;
  }

  public static TimerManager getTimerManager() {
    return timerManager;
  }

  public void setScene(Controller scene) {
    this.currentScene = scene;
  }

  public Controller getScene() {
    return this.currentScene;
  }

  public void setTime(String string) {
    this.getScene().setTime(string);
  }

  /**
   * Gets the initial game started state.
   *
   * @return the game started state
   */
  public GameState getGameStartedState() {
    return gameStartedState;
  }

  /**
   * Gets the guessing state.
   *
   * @return the guessing state
   */
  public GameState getGuessingState() {
    return guessingState;
  }

  /**
   * Gets the game over state.
   *
   * @return the game over state
   */
  public GameState getGameOverState() {
    return gameOverState;
  }

  /**
   * Gets the profession to be guessed.
   *
   * @return the profession to guess
   */
  public String getProfessionToGuess() {
    return professionToGuess;
  }

  /**
   * Gets the ID of the rectangle to be guessed.
   *
   * @return the rectangle ID to guess
   */
  public String getRectIdToGuess() {
    return rectIdToGuess;
  }

  /**
   * Gets the profession associated with a specific rectangle ID.
   *
   * @param rectangleId the rectangle ID
   * @return the profession associated with the rectangle ID
   */
  public String getProfession(String rectangleId) {
    return rectanglesToProfession.get(rectangleId);
  }

  /**
   * Handles the event when a rectangle is clicked.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @param rectangleId the ID of the clicked rectangle
   * @throws IOException if there is an I/O error
   */
  public void handleRectangleClick(MouseEvent event, String rectangleId) throws IOException {
    gameState.handleRectangleClick(event, rectangleId);
  }

  /**
   * Handles the event when the guess button is clicked.
   *
   * @throws IOException if there is an I/O error
   */
  public void handleGuessClick() throws IOException {
    gameState.handleGuessClick();
  }
}

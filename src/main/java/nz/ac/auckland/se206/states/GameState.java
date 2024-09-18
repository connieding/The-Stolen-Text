package nz.ac.auckland.se206.states;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.GameStateContext;

/**
 * Interface representing the state of the game. Defines methods to handle user interactions such as
 * clicking on a rectangle and making a guess.
 */
public abstract class GameState {

  protected final GameStateContext context;

  public GameState(GameStateContext context) {
    this.context = context;
  }

  /**
   * Handles the event when a rectangle is clicked.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @param rectangleId the ID of the clicked rectangle
   * @throws IOException if there is an I/O error
   */
  public abstract void handleRectangleClick(MouseEvent event, String rectangleId)
      throws IOException;

  public abstract void handleMapClicked(MouseEvent event, String rectangleId) throws IOException;

  public abstract void handleGuessClicked(MouseEvent event, String rectangleId) throws IOException;
}

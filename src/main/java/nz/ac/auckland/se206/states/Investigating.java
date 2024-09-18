package nz.ac.auckland.se206.states;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.GameStateContext;

public class Investigating extends GameState {

  private GameStateContext context;

  public Investigating(GameStateContext context) {
    super(context);
  }

  @Override
  public void handleRectangleClick(MouseEvent event, String rectangleId) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'handleRectangleClick'");
  }

  @Override
  public void handleMapClicked(MouseEvent event, String rectangleId) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'handleMapClicked'");
  }

  @Override
  public void handleGuessClicked(MouseEvent event, String rectangleId) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'handleGuessClicked'");
  }
}

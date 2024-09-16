package nz.ac.auckland.se206.states;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.GameStateContext;

public class ArchivistInterview extends GameState {

  private GameStateContext context;

  public ArchivistInterview(GameStateContext context) {
    super(context);
  }

  @Override
  public void handleRectangleClick(MouseEvent event, String rectangleId) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'handleRectangleClick'");
  }
}

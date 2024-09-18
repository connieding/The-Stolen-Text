package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameStateContext;

public class SuspectController implements Controller {

  @FXML private AnchorPane suspectPane;
  @FXML private ImageView suspectBg;
  @FXML private Text mainHead;
  @FXML private Rectangle buttonMap;
  @FXML private Rectangle buttonAccuse;
  @FXML private AnchorPane textPane;
  @FXML private Text textHead;
  @FXML private TextArea textHistory;
  @FXML private TextField textEntry;
  @FXML private Button textSend;
  @FXML private Label lblTimer;
  @FXML private SubScene mapSubScene;

  private GameStateContext context = new GameStateContext(this);

  /**
   * Initializes the historian suspect view.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  public void initialize() throws ApiProxyException {
    context.setScene(this);
  }

  @FXML
  private void onSendMessage(ActionEvent event) throws ApiProxyException, IOException {}

  @Override
  public void setTime(String timeRemaining) {
    lblTimer.setText(timeRemaining);
  }

  @Override
  public void handleGuessClicked(MouseEvent event) throws IOException {
    App.openGuessScene(buttonAccuse);
  }

  @Override
  public void handleMapClicked(MouseEvent event) throws IOException {
    App.openScene(buttonAccuse, "map");
  }
}

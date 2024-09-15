package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;

public class SuspectController {

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

  /**
   * Initializes the historian suspect view.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  public void initialize() throws ApiProxyException {
    // Any required initialization code can be placed here
  }

  @FXML
  private void onSendMessage(ActionEvent event) throws ApiProxyException, IOException {}
}

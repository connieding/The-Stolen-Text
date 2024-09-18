package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import nz.ac.auckland.apiproxy.chat.openai.ChatCompletionRequest;
import nz.ac.auckland.apiproxy.chat.openai.ChatCompletionResult;
import nz.ac.auckland.apiproxy.chat.openai.ChatMessage;
import nz.ac.auckland.apiproxy.chat.openai.Choice;
import nz.ac.auckland.apiproxy.config.ApiProxyConfig;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameStateContext;
import nz.ac.auckland.se206.prompts.PromptEngineering;

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
  @FXML private AnchorPane mapSubScene;

  private boolean isMapOut = false;
  private GameStateContext context = new GameStateContext(this);
  private ChatCompletionRequest chatHistory;

  /**
   * Initializes the historian suspect view.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  public void initialize() throws ApiProxyException {

    String character = textHead.getText().toLowerCase();

    context.setScene(this);
    System.out.println(character);
    textHistory.setText(PromptEngineering.getResource("responses", character, "txt"));

    try {
      ApiProxyConfig config = ApiProxyConfig.readConfig();

      chatHistory =
          new ChatCompletionRequest(config)
              .setN(1)
              .setTemperature(0.2)
              .setTopP(0.5)
              .setMaxTokens(100);
      chatHistory.addMessage(
          new ChatMessage("system", PromptEngineering.getResource("prompts", character, "txt")));
      chatHistory.addMessage(
          new ChatMessage(
              "assistant", PromptEngineering.getResource("responses", character, "txt")));
    } catch (ApiProxyException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void onSendMessage() throws ApiProxyException, IOException {
    String message = textEntry.getText();
    textEntry.setText("");
    // Request response from LLM
    Task<Void> chatTask =
        new Task<Void>() {

          @Override
          protected Void call() {
            ChatMessage response = new ChatMessage("user", message);
            chatHistory.addMessage(response);

            try {
              ChatCompletionResult chatResult = chatHistory.execute();
              Choice result = chatResult.getChoices().iterator().next();
              chatHistory.addMessage(result.getChatMessage());
              textHistory.setText(result.getChatMessage().getContent());

              // Speak the response
              // TextToSpeech.speak(result.getChatMessage().getContent(), currentSuspectId);
            } catch (ApiProxyException e) {
              e.printStackTrace();
            }

            return null;
          }
        };

    // Complete LLM response in a seperate thread, to prevent buffering
    Thread chatThread = new Thread(chatTask);
    chatThread.setDaemon(true);
    chatThread.start();
  }

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

    mapSubScene.setVisible(!mapSubScene.isVisible());
    mapSubScene.setDisable(!mapSubScene.isDisable());
    if (!isMapOut) {
      App.overlayMap(buttonMap);
    } else {
      App.hideMap(buttonMap);
    }
    isMapOut = !isMapOut;
  }

  /**
   * Sends message on enter key press
   *
   * @param event
   * @throws IOException
   * @throws ApiProxyException
   */
  @FXML
  private void onKeyPressed(KeyEvent event) throws IOException, ApiProxyException {
    if (event.getCode().equals(KeyCode.ENTER)) {
      onSendMessage();
    }
  }
}

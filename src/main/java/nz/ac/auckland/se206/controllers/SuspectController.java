package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import nz.ac.auckland.apiproxy.chat.openai.ChatCompletionRequest;
import nz.ac.auckland.apiproxy.chat.openai.ChatCompletionResult;
import nz.ac.auckland.apiproxy.chat.openai.ChatMessage;
import nz.ac.auckland.apiproxy.chat.openai.Choice;
import nz.ac.auckland.apiproxy.config.ApiProxyConfig;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.GameData;
import nz.ac.auckland.se206.prompts.PromptEngineering;

/**
 * This class is the controller for the historian suspect scene. It handles the chat history and the
 * text entry.
 */
public class SuspectController extends Controller {

  @FXML private AnchorPane suspectPane;
  @FXML private ImageView suspectBg;
  @FXML private Text mainHead;
  @FXML private AnchorPane textPane;
  @FXML private Text textHead;
  @FXML private TextArea textHistory;
  @FXML private TextField textEntry;
  @FXML private Button textSend;

  private ChatCompletionRequest chatHistory;
  private String character;

  /**
   * Initializes the historian suspect view.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  public void initialize() throws ApiProxyException {

    // Determine which suspect is being interacted with
    character = textHead.getText().toLowerCase();

    // Set the body text depending on whether the suspect has been met before
    if (!GameData.hasMetSuspect(character)) {
      textHistory.setText(PromptEngineering.getResource("responses", character, "txt"));
    } else {
      textHistory.setText(PromptEngineering.getResource("returning", character, "txt"));
    }

    // Initialize the chat history for the AI
    try {

      // Initialize the configs for the AI
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

  /**
   * Sends the message to the AI and displays the response.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   * @throws IOException if the scene is not found
   */
  @FXML
  private void onSendMessage() throws ApiProxyException, IOException {

    SoundController.playSound();

    String message = textEntry.getText();

    if (message.isEmpty()) {
      return;
    }

    switch (character) {
      case "archivist":
        GameData.setMetSuspect("archivist");
        break;
      case "collector":
        GameData.setMetSuspect("collector");
        break;
      case "historian":
        GameData.setMetSuspect("historian");
        break;
    }

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

  /**
   * Sends message on enter key press.
   *
   * @param event the key event
   * @throws IOException if the scene is not found
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  private void onKeyPressed(KeyEvent event) throws IOException, ApiProxyException {
    if (event.getCode().equals(KeyCode.ENTER)) {
      onSendMessage();
    }
  }
}

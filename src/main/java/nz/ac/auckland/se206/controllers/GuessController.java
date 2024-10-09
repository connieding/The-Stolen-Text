package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nz.ac.auckland.apiproxy.chat.openai.ChatCompletionRequest;
import nz.ac.auckland.apiproxy.chat.openai.ChatCompletionResult;
import nz.ac.auckland.apiproxy.chat.openai.ChatMessage;
import nz.ac.auckland.apiproxy.chat.openai.Choice;
import nz.ac.auckland.apiproxy.config.ApiProxyConfig;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameData;
import nz.ac.auckland.se206.prompts.PromptEngineering;

/**
 * This class is the controller for the guess scene. It handles the suspect selection, the motive
 * and evidence text fields, and the submit button.
 */
public class GuessController extends Controller {

  private static ApiProxyConfig config;

  private static String evaluateExplanation(
      String suspect, String motive, String evidence, String correctExplanation)
      throws IOException, ApiProxyException {

    // Give the AI the correct explanation and the player's explanation
    ChatCompletionRequest request =
        new ChatCompletionRequest(config)
            .addMessage(new ChatMessage("system", correctExplanation))
            .addMessage(
                new ChatMessage(
                    "user",
                    "The player selected: "
                        + suspect
                        + ". Motive: "
                        + motive
                        + ". Evidence: "
                        + evidence))
            .setMaxTokens(200)
            .setTemperature(0.5);

    // Get the AI's response
    try {
      ChatCompletionResult result = request.execute();
      Choice firstChoice = result.getChoices().iterator().next();
      String responseMessage = firstChoice.getChatMessage().getContent();
      return responseMessage;
    } catch (ApiProxyException e) {
      e.printStackTrace();
      return null;
    }
  }

  @FXML private ImageView archivistSelect;
  @FXML private ImageView collectorSelect;
  @FXML private ImageView historianSelect;

  @FXML private ImageView circleArchivist;
  @FXML private ImageView circleCollector;
  @FXML private ImageView circleHistorian;

  @FXML private Text archivistTitle;
  @FXML private Text collectorTitle;
  @FXML private Text historianTitle;

  @FXML private TextArea textMotive;
  @FXML private TextArea textEvidence;

  private String selectedSuspect = null; // Variable to hold the selected suspect
  private boolean suspectCorrect = true; // Variable to hold whether the player is guessing

  /** Initialize the guess scene and set the game state to guessing. */
  @FXML
  public void initialize() {

    // Set the game state to guessing
    GameData.setGuessing(true);

    // Initialize the API proxy config
    try {
      config = ApiProxyConfig.readConfig();
    } catch (ApiProxyException e) {
      e.printStackTrace();
    }

    // Prep the indicators of who is selected
    hideAllCircles();
    archivistSelect.setOnMouseClicked(event -> setSelectedSuspect("archivist"));
    collectorSelect.setOnMouseClicked(event -> setSelectedSuspect("collector"));
    historianSelect.setOnMouseClicked(event -> setSelectedSuspect("historian"));
  }

  /**
   * Update UI to show selected suspect with red circle.
   *
   * @param suspect
   */
  private void setSelectedSuspect(String suspect) {
    SoundController.playCircle();

    // Set the selected suspect
    selectedSuspect = suspect;

    // Update the UI to show the selected suspect
    hideAllCircles();
    archivistSelect.setOpacity(0.7);
    collectorSelect.setOpacity(0.7);
    historianSelect.setOpacity(0.7);

    // Show the selected suspect
    if (suspect.equals("archivist")) {
      archivistSelect.setOpacity(1.0);
      circleArchivist.setVisible(true);
    } else if (suspect.equals("collector")) {
      collectorSelect.setOpacity(1.0);
      circleCollector.setVisible(true);
    } else if (suspect.equals("historian")) {
      historianSelect.setOpacity(1.0);
      circleHistorian.setVisible(true);
    }
  }

  /** Hide all the circles of the suspects to player. */
  private void hideAllCircles() {

    // Hide all the circles
    circleArchivist.setVisible(false);
    circleCollector.setVisible(false);
    circleHistorian.setVisible(false);
  }

  /**
   * Handle the submit button click and evaluate the player's explanation.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   * @throws IOException if the feedback scene is not found
   */
  public void handleSubmitClicked() throws ApiProxyException, IOException {
    SoundController.playSound();

    // If the correct suspect hasn't been selected, show the failed scene
    if (selectedSuspect == null || selectedSuspect != "collector") {
      // App.openScene(textEvidence, "failed");
      // suspectCorrect = false;
      SoundController.guessClick(
          () -> {
            SoundController.playFail(); // Play the fail sound after the button click sound
            try {
              App.openScene(textEvidence, "failed");
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
    } else {
      // Play the appriopriate success sound
      SoundController.guessClick(
          () -> {
            SoundController.playSuccess();
          });
    }

    // Get the motive and evidence from the text fields
    String motive = textMotive.getText().trim();
    String evidence = textEvidence.getText().trim();

    // Get the correct explanation from the prompts
    String correctExplanation = PromptEngineering.getResource("prompts", "marking", "txt");

    // Evaluate the explanation
    String feedback = evaluateExplanation(selectedSuspect, motive, evidence, correctExplanation);

    // Open the feedback scene
    FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/feedback.fxml"));
    Parent root = loader.load();
    FeedbackController feedbackController = loader.getController();
    feedbackController.setFeedback(feedback);
    Stage stage = (Stage) textMotive.getScene().getWindow();
    stage.setScene(new Scene(root));
  }
}

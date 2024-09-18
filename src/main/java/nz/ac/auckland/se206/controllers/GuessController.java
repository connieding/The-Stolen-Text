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
import nz.ac.auckland.apiproxy.chat.openai.Choice;
import nz.ac.auckland.apiproxy.config.ApiProxyConfig;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.prompts.PromptEngineering;

public class GuessController {

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
  private ApiProxyConfig config;

  @FXML
  public void initialize() {
    try {
      config = ApiProxyConfig.readConfig();
    } catch (ApiProxyException e) {
      e.printStackTrace();
    }

    hideAllCircles();

    archivistSelect.setOnMouseClicked(event -> setSelectedSuspect("Archivist"));
    collectorSelect.setOnMouseClicked(event -> setSelectedSuspect("Collector"));
    historianSelect.setOnMouseClicked(event -> setSelectedSuspect("Historian"));
  }

  private void setSelectedSuspect(String suspect) {
    this.selectedSuspect = suspect;

    hideAllCircles();

    archivistSelect.setOpacity(0.7);
    collectorSelect.setOpacity(0.7);
    historianSelect.setOpacity(0.7);

    if (suspect.equals("Archivist")) {
      archivistSelect.setOpacity(1.0);
      circleArchivist.setVisible(true);
    } else if (suspect.equals("Collector")) {
      collectorSelect.setOpacity(1.0);
      circleCollector.setVisible(true);
    } else if (suspect.equals("Historian")) {
      historianSelect.setOpacity(1.0);
      circleHistorian.setVisible(true);
    }
  }

  private void hideAllCircles() {
    circleArchivist.setVisible(false);
    circleCollector.setVisible(false);
    circleHistorian.setVisible(false);
  }

  public void handleSubmitClicked() throws ApiProxyException, IOException {
    String motive = textMotive.getText().trim();
    String evidence = textEvidence.getText().trim();

    String correctExplanation = PromptEngineering.getResource("prompts", "explanation", "txt");

    String feedback = evaluateExplanation(selectedSuspect, motive, evidence, correctExplanation);

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/feedback.fxml"));
    Parent root = loader.load();

    FeedbackController feedbackController = loader.getController();
    feedbackController.setFeedback(feedback);

    Stage stage = (Stage) textMotive.getScene().getWindow();
    stage.setScene(new Scene(root));
  }

  private String evaluateExplanation(
      String suspect, String motive, String evidence, String correctExplanation)
      throws IOException, ApiProxyException {
    ChatCompletionRequest request =
        new ChatCompletionRequest(config)
            .addMessage("system", "You are a detective game assistant.")
            .addMessage(
                "user",
                "The player selected: "
                    + suspect
                    + ". Motive: "
                    + motive
                    + ". Evidence: "
                    + evidence)
            .addMessage("system", "The correct explanation is: " + correctExplanation)
            .addMessage(
                "system",
                "Please compare the player's explanation to the correct one and provide feedback.")
            .setMaxTokens(200)
            .setTemperature(0.5);

    ChatCompletionResult result = request.execute();

    Choice firstChoice = null;
    for (Choice choice : result.getChoices()) {
      firstChoice = choice;
      break;
    }

    if (firstChoice != null) {
      String responseMessage = firstChoice.getChatMessage().getContent();
      return responseMessage;
    } else {
      return " Error: No response received from the AI.";
    }
  }
}

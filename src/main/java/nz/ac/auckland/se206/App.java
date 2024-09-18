package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nz.ac.auckland.se206.speech.FreeTextToSpeech;

/**
 * This is the entry point of the JavaFX application. This class initializes and runs the JavaFX
 * application.
 */
public class App extends Application {

  private static Scene scene;
  private static TimerManager timerManager;

  /**
   * The main method that launches the JavaFX application.
   *
   * @param args the command line arguments
   */
  public static void main(final String[] args) {
    launch();
  }

  /**
   * Sets the root of the scene to the specified FXML file.
   *
   * @param fxml the name of the FXML file (without extension)
   * @throws IOException if the FXML file is not found
   */
  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFxml(fxml));
  }

  /**
   * Loads the FXML file and returns the associated node. The method expects that the file is
   * located in "src/main/resources/fxml".
   *
   * @param fxml the name of the FXML file (without extension)
   * @return the root node of the FXML file
   * @throws IOException if the FXML file is not found
   */
  private static Parent loadFxml(final String fxml) throws IOException {
    return new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml")).load();
  }

  public static void overlayMap(Node button) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/map.fxml"));
    Pane root = loader.load();
    scene = new Scene(root);
    (((Pane) button.getScene().lookup("#mapSubScene")).getChildren()).add(root);
    // ((Pane) scene.getRoot()).getChildren().add(button.getScene().lookup("#mapSubScene"));
  }

  public static void hideMap(Node button) throws IOException {
    ((Pane) button.getScene().lookup("#mapSubScene")).getChildren().clear();
  }

  public static void openScene(Node button, String newScene) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/" + newScene + ".fxml"));
    Parent root = loader.load();

    Stage stage = (Stage) button.getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    handleWindowClose();
    int remaining = 300;
    if (timerManager != null) {
      remaining = timerManager.getTime();
    }
    timerManager = TimerManager.getInstance();
    Label timerLabel = (Label) scene.lookup("#lblTimer");
    timerManager.startTimer(remaining, timerLabel);
    stage.setOnCloseRequest(event -> handleWindowClose());
    root.requestFocus();
  }

  public static void openGuessScene(Node button) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/guess.fxml"));
    Parent root = loader.load();

    Stage stage = (Stage) button.getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    handleWindowClose();
    timerManager = TimerManager.getInstance();
    Label timerLabel = (Label) scene.lookup("#lblTimer");
    timerManager.startTimer(60, timerLabel);
    stage.setOnCloseRequest(event -> handleWindowClose());
    root.requestFocus();
  }

  /**
   * This method is invoked when the application starts. It loads and shows the "room" scene.
   *
   * @param stage the primary stage of the application
   * @throws IOException if the "src/main/resources/fxml/room.fxml" file is not found
   */
  @Override
  public void start(final Stage stage) throws IOException {
    Parent root = loadFxml("start");
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  private static void handleWindowClose() {
    FreeTextToSpeech.deallocateSynthesizer();

    if (timerManager != null) {
      timerManager.stopTimer();
    }
  }
}

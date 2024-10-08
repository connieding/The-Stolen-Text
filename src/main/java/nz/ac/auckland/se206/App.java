package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nz.ac.auckland.se206.speech.FreeTextToSpeech;

/**
 * This is the entry point of the JavaFX application. This class initializes and runs the JavaFX
 * application.
 */
public class App extends Application {

  private static Scene scene;
  private static TimerManager timerManager;
  private static GameData data;

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

  /**
   * This method is invoked when the application is closed. It deallocates the synthesizer and stops
   * the timer.
   */
  public static void handleWindowClose() {
    FreeTextToSpeech.deallocateSynthesizer();

    if (timerManager != null) {
      timerManager.stopTimer();
    }
  }

  /**
   * Overlays the map on the current scene.
   *
   * @param button the button that was clicked to show the map
   * @throws IOException if the map FXML file is not found
   */
  public static void overlayMap(Node button) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/map.fxml"));
    Pane root = loader.load();
    scene = new Scene(root);
    (((Pane) button.getScene().lookup("#mapSubScene")).getChildren()).add(root);
  }

  /**
   * Hides the map from the current scene.
   *
   * @param button the button that was clicked to hide the map
   * @throws IOException if the map subscene is not found
   */
  public static void hideMap(Node button) throws IOException {
    ((Pane) button.getScene().lookup("#mapSubScene")).getChildren().clear();
  }

  /**
   * Overlays the warning on the current scene.
   *
   * @throws IOException if the warning FXML file is not found
   */
  public static void overlayWarning() throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/warning.fxml"));
    Pane warningPane = loader.load();
    Pane warningSubScene = (Pane) App.scene.lookup("#warningSubScene");
    warningSubScene.getChildren().add(warningPane);
    warningSubScene.setVisible(true);
  }

  /** Hides the warning from the current scene. */
  public static void hideWarning() {
    Pane warningSubScene = (Pane) App.scene.lookup("#warningSubScene");
    warningSubScene.setVisible(false);
  }

  /**
   * Opens a new scene and closes the previous scene.
   *
   * @param button the button that was clicked to open the new scene
   * @param newScene the name of the new scene (without extension)
   * @throws IOException if the new scene FXML file is not found
   */
  public static void openScene(Node button, String newScene) throws IOException {

    // Load the new scene
    FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/" + newScene + ".fxml"));
    Parent root = loader.load();
    Stage stage = (Stage) button.getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.5;
    double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.5;
    stage.setX(x);
    stage.setY(y);

    // Close the previous scene
    handleWindowClose();

    // Start the timer
    int remaining = 300;
    if (timerManager != null) {
      remaining = timerManager.getTime();
    }
    timerManager = TimerManager.getInstance(data);

    // Set the timer label
    Label timerLabel = (Label) scene.lookup("#lblTimer");
    timerManager.startTimer(remaining, timerLabel);

    // Ensure the timer stops when the window is closed
    stage.setOnCloseRequest(event -> handleWindowClose());
    root.requestFocus();
  }

  /**
   * Opens the guess scene and closes the previous scene.
   *
   * @param button the button that was clicked to open the guess scene
   * @throws IOException if the guess scene FXML file is not found
   */
  public static void openGuessScene(Node button) throws IOException {

    // Load the guess scene
    FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/guess.fxml"));
    Parent root = loader.load();
    Stage stage = (Stage) button.getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

    // Tell the game data to use the guess controller
    data.setGuessController(loader.getController());

    // Close the previous scene
    handleWindowClose();

    // Start the timer
    timerManager = TimerManager.getInstance(data);
    Label timerLabel = (Label) scene.lookup("#lblTimer");
    timerManager.startTimer(60, timerLabel);

    // Ensure the timer stops when the window is closed
    stage.setOnCloseRequest(event -> handleWindowClose());
    root.requestFocus();
  }

  /**
   * Opens the feedback scene and closes the previous scene.
   *
   * @param button the button that was clicked to open the feedback scene
   * @throws IOException if the feedback scene FXML file is not found
   */
  public static void reset() {
    timerManager.stopTimer();
    timerManager = null;
    data = new GameData();
  }

  /**
   * This method is invoked when the application starts. It loads and shows the "room" scene.
   *
   * @param stage the primary stage of the application
   * @throws Exception
   */
  @Override
  public void start(final Stage stage) throws Exception {
    data = new GameData();
    Parent root = loadFxml("start");
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}

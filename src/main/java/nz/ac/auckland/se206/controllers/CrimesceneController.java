package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameData;

public class CrimesceneController extends Controller {

  @FXML private AnchorPane crimescenePane;
  @FXML private ImageView crimesceneBg;
  @FXML private Rectangle buttonMap;
  @FXML private Rectangle clueOne;
  @FXML private Rectangle clueTwo;
  @FXML private Rectangle clueThree;
  @FXML private Text crimesceneHead;
  @FXML private Rectangle buttonAccuse;
  @FXML private Label lblTimer;
  @FXML private AnchorPane mapSubScene;
  @FXML private Polygon shardOne;
  @FXML private Polygon shardTwo;
  @FXML private Polygon shardThree;
  @FXML private Polygon handkerchiefClue;
  @FXML private Polygon bookshelfClue;

  @Override
  public void initialize() throws ApiProxyException {}

  public void handleRectangleClicked(MouseEvent event) throws IOException {
    GameData.setUsedClue(true);
    App.openScene(((Rectangle) event.getSource()), ((Rectangle) event.getSource()).getId());
  }

  public void handleClueEntered(MouseEvent event) {
    Rectangle source = (Rectangle) event.getSource();
    String id = source.getId();
    if (id.equals("clueGlass")) {
      shardOne.setOpacity(0.7);
      shardTwo.setOpacity(0.7);
      shardThree.setOpacity(0.7);
    } else if (id.equals("cluePaper")) {
      handkerchiefClue.setOpacity(0.7);
    } else {
      bookshelfClue.setOpacity(0.7);
    }
  }

  public void handleClueExited(MouseEvent event) {
    Rectangle source = (Rectangle) event.getSource();
    String id = source.getId();
    if (id.equals("clueGlass")) {
      shardOne.setOpacity(0);
      shardTwo.setOpacity(0);
      shardThree.setOpacity(0);
    } else if (id.equals("cluePaper")) {
      handkerchiefClue.setOpacity(0);
    } else {
      bookshelfClue.setOpacity(0);
    }
  }
}

package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;

public abstract class ClueController extends Controller {

  @FXML protected AnchorPane mapSubScene;
  @FXML protected Rectangle buttonMap;
  @FXML protected Rectangle buttonAccuse;

  protected boolean isMapOut = false;

  @Override
  public void initialize() throws ApiProxyException {}
}

package nz.ac.auckland.se206.controllers;

import javafx.scene.Node;

public class Draggable {

  private double mouseX;
  private double mouseY;

  public void makeDraggable(Node node) {

    node.setOnMousePressed(
        mouseEvent -> {
          mouseX = mouseEvent.getX();
          mouseY = mouseEvent.getY();
        });

    node.setOnMouseDragged(
        mouseEvent -> {
          node.setLayoutX(mouseEvent.getSceneX() - mouseX);
          node.setLayoutY(mouseEvent.getSceneY() - mouseY);
        });
  }
}

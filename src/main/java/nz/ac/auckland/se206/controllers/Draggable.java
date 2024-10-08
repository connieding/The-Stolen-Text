package nz.ac.auckland.se206.controllers;

import javafx.scene.Node;

public class Draggable {

  // Variables to store the mouse coordinates
  private double mouseX;
  private double mouseY;

  public void makeDraggable(Node node) {

    // Set the mouse coordinates when the mouse is pressed
    node.setOnMousePressed(
        mouseEvent -> {
          mouseX = mouseEvent.getX();
          mouseY = mouseEvent.getY();
        });

    // Move the node when the mouse is dragged
    node.setOnMouseDragged(
        mouseEvent -> {
          node.setLayoutX(mouseEvent.getSceneX() - mouseX);
          node.setLayoutY(mouseEvent.getSceneY() - mouseY);
        });

    // Set the node as dragged when the mouse is released
    node.setOnMouseReleased(
        mouseEvent -> {
          ClueGlassController.playGlassDropSound();
          ClueGlassController.setDraggedMap(node, true);
        });
  }
}

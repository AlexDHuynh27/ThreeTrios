package cs3500.threetrios.view;

import java.awt.Color;

public interface ThreeTriosFeatures {
  void setSelected(Color color, int selected);

  void playSelectedToGrid(int row, int col);

  /**
   * Causes the controller to update the view with new information from the model.
   */
  void update();
}

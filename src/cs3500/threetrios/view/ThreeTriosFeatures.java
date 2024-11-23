package cs3500.threetrios.view;

import java.awt.Color;

public interface ThreeTriosFeatures {
  void setSelected(Color color, int selected);

  void playSelectedToGrid(int row, int col);

  void update();

  void checkTurn();
}

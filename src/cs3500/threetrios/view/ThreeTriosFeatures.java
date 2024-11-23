package cs3500.threetrios.view;

import java.awt.Color;

public interface ThreeTriosFeatures {

  /**
   * Sets a card to be selected in the given colors hand for future use.
   * If the card is already selected, it will de-select it. If it is not the given color's
   * turn no card will be selected. Also repaints the view.
   * @param color The color for which hand to select the card from.
   * @param selected The idx of the card to select. Idx-0
   */
  void setSelected(Color color, int selected);

  /**
   * Plays the currently selected card to the grid. A message pops up if the move is invalid,
   * or it is not the players turn.
   *
   * @param row the row to play the selected card to. Idx-0
   * @param col the column to play the selected card to. Idx-0
   */
  void playSelectedToGrid(int row, int col);

  /**
   * Causes the controller to update the view with new information from the model.
   */
  void update();
}

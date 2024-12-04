package cs3500.threetrios.view;

import java.awt.Color;

/**
 * Represents a Features interface that handles functionality for a ThreeTrios Game,
 * based on specific events, such as mouse or keyboard events. Handles functioanlity for Listeners.
 */
public interface ThreeTriosFeatures {
  /**
   * Sets the selected card of the given colors hand, based on the given index. Will not select
   * a card if it is not that colors turn.
   * @param color Color of the hand.
   * @param selected Index of the card to select. Idx-0.
   */
  void setSelected(Color color, int selected);

  /**
   * Plays the currently selected card to the given row and col of the grid of a ThreeTriosGame.
   * @param row row of the grid to play on. Idx-0.
   * @param col column of the grid to play on. Idx-0.
   */
  void playSelectedToGrid(int row, int col);

  /**
   * Checks if the game is over, if it is it will display a message of who won and what their score
   * was. If the game is not over it will a pop-up message will display who's turn it is.
   */
  void checkTurnAndWinner();

  /**
   * Causes the controller to update the view with new information from the model.
   */
  void update();
}

package cs3500.threetrios.provider.model;

import java.util.List;

/**
 * Represents the game board for Three Trios.
 * It is a rectangular grid container of cells.
 * Contains two types of cell, a hole (no placement)
 * and a card cell that is either empty or contains a card.
 */
public interface Board {

  /**
   * Returns the cell at the specified index.
   *
   * @param row of the desired cell (0-based index).
   * @param col of the desired cell (0-based index).
   * @return the Cell at the given index.
   */
  Cell getCellAt(int row, int col);

  /**
   * Returns a copy of the grid.
   *
   * @return a copy of the grid.
   */
  List<List<Object>> getAllCells();

  /**
   * Executes a battle phase, given the location of the last played card.
   *
   * @param row of the last played card (0-based index).
   * @param col of the last played card (0-based index).
   * @throws IllegalArgumentException if there is no card at (row, col)
   */
  void executeBattlePhase(int row, int col);

  /**
   * Returns a list of cards on this board belonging to the given Player.
   *
   * @param player is the player whose cards we want to return.
   * @return a list of cards.
   */
  List<Card> getPlayerCards(Player player);

  /**
   * Play the given card to the given location.
   *
   * @param row  0-indexed row
   * @param col  0-indexed col
   * @param card Card to play
   * @throws IllegalArgumentException if (row, col) is out of bounds or if there's
   *                                  already a card there.
   */
  void playToBoard(int row, int col, Card card);

  /**
   * Returns whether there are any open card cells.
   *
   * @return whether the game is over
   */
  boolean isGameOver();

  /**
   * Returns a deep copy of this object.
   * @return a deep copy of this object.
   */
  Board cloneBoard();
}
